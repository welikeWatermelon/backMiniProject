package ssafy.project07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.project07.domain.Pharmacist;
import ssafy.project07.domain.User;
import ssafy.project07.domain.enums.Gender;
import ssafy.project07.domain.enums.UserRole;
import ssafy.project07.dto.user.*;
import ssafy.project07.repository.user.UserRepository;
import ssafy.project07.security.JwtTokenProvider;

@Service
@RequiredArgsConstructor
// 생성자 안만들어도 됨
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider; //

    public String userRegister(UserRegisterRequest dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {//isPresent() : 객체 있는지 없는지 !
            return "이미 같은 이메일이 있습니다.";
        }
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setBirthDate(dto.getBirthDate());
        user.setGender(Gender.valueOf(dto.getGender().toUpperCase()));
        user.setNickname(dto.getNickname());
        user.setProfileImage(dto.getProfileImage());
        user.setRole(UserRole.valueOf(dto.getRole().toUpperCase()));

        if (user.getRole() == UserRole.PHARMACIST) {
            Pharmacist profile = new Pharmacist();
            profile.setLicenseNumber(dto.getLicenseNumber());
            profile.setHospitalName(dto.getHospitalName());
            profile.setUser(user);

            user.setPharmacist(profile);
        }
        userRepository.save(user);
        return "회원가입 성공";
    }

    public UserLoginResponse userLogin(UserLoginRequest userLoginRequest) {
        String email = userLoginRequest.getEmail();
        String password = userLoginRequest.getPassword();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일의 유저가 존재하지 않습니다."));

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 올바르지 않습니다.");
        }

        // ✅ 토큰 발급
        String token = jwtTokenProvider.createToken(user.getEmail());

        // ✅ 로그인 응답 반환
        return new UserLoginResponse(token,user.getId());
    }


//    public User userLogin(UserLoginRequest userLoginRequest) {
//        String email = userLoginRequest.getEmail();
//        String password = userLoginRequest.getPassword();
//
//        // 1. 이메일로 유저 조회
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new IllegalArgumentException("해당 이메일의 유저가 존재하지 않습니다."));
//
//
//        // 2. 비밀번호 검증
//        if (!user.getPassword().equals(password)) {
//            throw new IllegalArgumentException("비밀번호가 올바르지 않습니다.");
//        }
//
//        // 3, 성공 시 User 반환
//        return user;
//    }

    public UserProfileResponse profileGet(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        UserProfileResponse response = new UserProfileResponse();
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setBirthDate(user.getBirthDate());
        response.setGender(user.getGender().name());
        response.setNickname(user.getNickname());
        response.setProfileImage(user.getProfileImage());

        return response;
    }

    public void profilePut(Long userId, UserProfileRequest userProfileRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("에러 !"));

        user.setName(userProfileRequest.getName());
        user.setBirthDate(userProfileRequest.getBirthDate());
        user.setGender(Gender.valueOf(userProfileRequest.getGender()));
        user.setNickname(userProfileRequest.getNickname());
        user.setProfileImage(userProfileRequest.getProfileImage());

        userRepository.save(user);
    }
}
