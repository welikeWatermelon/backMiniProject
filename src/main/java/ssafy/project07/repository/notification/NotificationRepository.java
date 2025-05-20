package ssafy.project07.repository.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project07.domain.notification.Notification;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserIdOrderByNotifiedAtDesc(Long userId);
}
