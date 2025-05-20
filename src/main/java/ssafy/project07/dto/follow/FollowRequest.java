package ssafy.project07.dto.follow;

import lombok.Data;

@Data
public class FollowRequest {
//    private Long userId;         // 팔로우하는 사람
    private Long pharmacistId;   // 팔로우 대상 약사
}
