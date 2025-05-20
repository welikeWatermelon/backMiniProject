package ssafy.project07.dto.notification;

import lombok.Data;
import ssafy.project07.domain.notification.Notification;

import java.time.LocalDateTime;

// notification 목록을 반환할 때 사용되는 DTO
// 0520 새코드
@Data
public class NotificationResponse {
    private Long id;
    private String message;
    private boolean read;
    private LocalDateTime notifiedAt;

    public static NotificationResponse fromEntity(Notification noti) {
        NotificationResponse dto = new NotificationResponse();
        dto.setId(noti.getId());
        dto.setMessage(noti.getMessage());
        dto.setRead(noti.isRead());
        dto.setNotifiedAt(noti.getNotifiedAt());
        return dto;
    }
}
