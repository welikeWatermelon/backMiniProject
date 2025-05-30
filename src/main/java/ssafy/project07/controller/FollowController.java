package ssafy.project07.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ssafy.project07.domain.user.User;
import ssafy.project07.dto.follow.FollowRequest;
import ssafy.project07.dto.follow.FollowResponse;
import ssafy.project07.service.FollowService;
import ssafy.project07.dto.column.PharmacistColumnListResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/follows")
public class FollowController {

    private final FollowService followService;

    // 보안 OK
    @PostMapping
    public ResponseEntity<Void> follow(@RequestBody FollowRequest request,
                                       @AuthenticationPrincipal User user) {
        followService.follow(user,request);
        return ResponseEntity.ok().build();
    }

    // 보안 OK
    @GetMapping("/my")
    public ResponseEntity<List<FollowResponse>> getMyFollows(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(followService.getMyFollows(user.getId()));
    }

    // 칼럼보기 누를 때, 작용하는 코드 새로운코드 0521 (+ dto추가와 service메서드 수정)
    @PostMapping("/pharmacist")
    public ResponseEntity<PharmacistColumnListResponse> getPharmacistColumns(@RequestBody FollowRequest request,
                                                                             @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(followService.getPharmacistColumns(request.getPharmacistId()));
    }
}
