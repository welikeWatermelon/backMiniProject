package ssafy.project07.dto.community;

import lombok.Data;

@Data
public class CommunityRequest {
    private String title;
    private String content;
    private Long userId;
}
