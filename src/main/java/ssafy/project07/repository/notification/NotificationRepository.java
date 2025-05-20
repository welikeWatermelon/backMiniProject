package ssafy.project07.repository.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project07.domain.notification.Notification;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserIdOrderByNotifiedAtDesc(Long userId);

    // 오늘꺼 알림 조회 레포지토리
    List<Notification> findByUserIdAndNotifiedAtBetween(Long userId, LocalDateTime start, LocalDateTime end);
}
