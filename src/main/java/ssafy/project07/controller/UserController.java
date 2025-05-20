package ssafy.project07.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ssafy.project07.domain.User;
import ssafy.project07.dto.user.*;
import ssafy.project07.repository.user.UserRepository;
import ssafy.project07.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public void login(@RequestBody UserRegisterRequest userRegisterRequest) {
        userService.userRegister(userRegisterRequest);
    }

//    @PostMapping("/login")
//    public void login(@RequestBody UserLoginRequest userLoginRequest) {
//        userService.userLogin(userLoginRequest);
//    }

    // 토큰 발급
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        UserLoginResponse response = userService.userLogin(userLoginRequest);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> profile(@AuthenticationPrincipal User user) {
        return  ResponseEntity.ok(userService.profileGet(user.getId()));

    }

    @PutMapping("/profile")
    public void profilePut(@RequestParam Long userId, @RequestBody UserProfileRequest userProfileRequest) {
        userService.profilePut(userId, userProfileRequest);
    }
}
