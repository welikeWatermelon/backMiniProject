package ssafy.project07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.project07.domain.Notification;
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
}
