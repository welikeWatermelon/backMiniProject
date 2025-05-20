package ssafy.project07.dto.follow;

import lombok.Data;

@Data
public class FollowResponse {
    private Long pharmacistId;
    private String name;
    private String title;
    private String profileImage;
}
