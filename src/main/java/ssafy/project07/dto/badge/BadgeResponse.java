package ssafy.project07.dto.badge;

import lombok.Data;

@Data
public class BadgeResponse {
    private Long id;
    private String name;
    private String imageUrl;
    private String description;
}
