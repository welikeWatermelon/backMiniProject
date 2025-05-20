package ssafy.project07.dto.notification;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationResponse {
    private Long id;
    private String message;
    private boolean isRead;
    private LocalDateTime notifiedAt;
}
