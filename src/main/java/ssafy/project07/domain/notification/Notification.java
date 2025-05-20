package ssafy.project07.domain.notification;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ssafy.project07.domain.user.User;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
// 시스템이 사용자에게 발송한 알림 기록. 메시지, 읽음 여부, 발송 시각 포함.
public class Notification {
    @Id @GeneratedValue
    private Long id;
    private String message;
    private boolean isRead; // 0520 수정
    private LocalDateTime notifiedAt;
    @ManyToOne
    private User user;
}