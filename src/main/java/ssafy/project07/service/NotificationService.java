package ssafy.project07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.project07.domain.notification.Notification;
import ssafy.project07.domain.user.User;
import ssafy.project07.dto.notification.NotificationRequest;
import ssafy.project07.dto.notification.NotificationResponse;
import ssafy.project07.repository.notification.NotificationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public List<NotificationResponse> getUserNotifications(Long userId) {
        return notificationRepository.findByUserIdOrderByNotifiedAtDesc(userId)
                .stream().map(n -> {
                    NotificationResponse res = new NotificationResponse();
                    res.setId(n.getId());
                    res.setMessage(n.getMessage());
                    res.setRead(n.isIsRead());
                    res.setNotifiedAt(n.getNotifiedAt());
                    return res;
                }).collect(Collectors.toList());
    }

    public void markAsRead(Long notificationId) {
        Notification noti = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 알림이 존재하지 않습니다."));
        noti.setIsRead(true);
        notificationRepository.save(noti);
    }

    // 새로운 메서드 0520
    public void registerNotification(Long userId, NotificationRequest request) {
        Notification notification = new Notification();
        notification.setMessage(request.getMessage());
        notification.setIsRead(false); // 새 알림은 기본적으로 읽지 않음
        notification.setNotifiedAt(request.getNotifiedAt());

        User user = new User(); // ID만 설정해서 연관관계 연결
        user.setId(userId);
        notification.setUser(user);

        notificationRepository.save(notification);
    }

}
