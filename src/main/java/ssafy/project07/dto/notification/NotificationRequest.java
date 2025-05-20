package ssafy.project07.dto.notification;

import lombok.Data;

import java.time.LocalDateTime;

//0520 알림 추가
@Data
public class NotificationRequest {
    private Long id;
    private String message;
//    private boolean isRead;
    private LocalDateTime notifiedAt;
}
