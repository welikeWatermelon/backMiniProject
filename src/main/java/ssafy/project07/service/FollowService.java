package ssafy.project07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.project07.domain.column.Column;
import ssafy.project07.domain.follow.Follow;
import ssafy.project07.domain.user.Pharmacist;
import ssafy.project07.domain.user.User;
import ssafy.project07.dto.column.ColumnSummaryDto;
import ssafy.project07.dto.column.PharmacistColumnListResponse;
import ssafy.project07.dto.follow.FollowRequest;
import ssafy.project07.dto.follow.FollowResponse;
import ssafy.project07.repository.column.ColumnRepository;
import ssafy.project07.repository.follow.FollowRepository;
import ssafy.project07.repository.pharmacist.PharmacistRepository;
import ssafy.project07.repository.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final PharmacistRepository pharmacistRepository;

    // 0521 추가 코드
    private final ColumnRepository columnRepository;


    public void follow(User user, FollowRequest request) {
        System.out.println(request.getPharmacistId()); // 약사 id가 전달됨 , 약사 id -> 사용자 id를 가져와야함?
        Pharmacist pharmacist = pharmacistRepository.findById(request.getPharmacistId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 약사입니다.")); // 해당 약사를 가져옴

        Follow follow = new Follow();
        follow.setUser(user); // 이게 왜 필요하냐? 필요없을걸??????? 아 팔로우 엔티티 : 누구랑 누구랑 이어줬냐 가 필요하지 그래서 이거 필요함
        follow.setPharmacist(pharmacist);


        followRepository.save(follow); // follow 엔티티에 넣어줌
    }

    public List<FollowResponse> getMyFollows(Long userId) {
        // userId로 찾은 follow들(약사들을)을 모두 불러와서 follow 라고 표시
        return followRepository.findByUserId(userId).stream()
                .map(follow -> {
                    Pharmacist p = follow.getPharmacist();

                    System.out.println("🔥 pharmacist in follow = " + (p != null ? p.getName() : "null"));


                    FollowResponse res = new FollowResponse();
                    res.setPharmacistId(p.getId());
                    res.setName(p.getName());
                    res.setTitle(p.getTitle());
                    res.setProfileImage(p.getProfileImage());
                    // 모든 follow들을 res로 바꾸겠다. (res : followResponse)
                    return res;
                }).collect(Collectors.toList());
    }

    // 추가 코드 0521
    public PharmacistColumnListResponse getPharmacistColumns(Long pharmacistId) {
        Pharmacist pharmacist = pharmacistRepository.findById(pharmacistId)
                .orElseThrow(() -> new IllegalArgumentException("해당 약사가 존재하지 않습니다."));

        List<Column> columns = columnRepository.findByPharmacistId(pharmacistId);

        List<ColumnSummaryDto> columnList = columns.stream()
                .map(c -> new ColumnSummaryDto(c.getId(), c.getTitle()))
                .toList();

        return new PharmacistColumnListResponse(pharmacist.getName(), columnList);
    }
}
