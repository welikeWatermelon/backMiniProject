package ssafy.project07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.project07.domain.notification.Notification;
import ssafy.project07.domain.user.User;
import ssafy.project07.dto.notification.NotificationRequest;
import ssafy.project07.dto.notification.NotificationResponse;
import ssafy.project07.repository.notification.NotificationRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
                    res.setRead(n.isRead());
                    res.setNotifiedAt(n.getNotifiedAt());
                    return res;
                }).collect(Collectors.toList());
    }

    // 새로운 메서드 0520 - 알림 체크하고 유지하는 코드 -> DB 반영
    public void markAsRead(Long notificationId) {
        Notification noti = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 알림이 존재하지 않습니다."));

        noti.setRead(true); // ✅ 읽음 처리
        notificationRepository.save(noti); // ✅ 저장 필요
    }

    // 새로운 메서드 0520 - 캘린더에서 알림 등록
    public void registerNotification(Long userId, NotificationRequest request) {
        Notification notification = new Notification();
        notification.setMessage(request.getMessage());
        notification.setRead(false); // 새 알림은 기본적으로 읽지 않음
        notification.setNotifiedAt(request.getNotifiedAt());

        User user = new User(); // ID만 설정해서 연관관계 연결
        user.setId(userId);
        notification.setUser(user);

        notificationRepository.save(notification);
    }

    // 새로운 메서드 0520 알림 보는 메서드
    public List<NotificationResponse> getTodayNotifications(Long userId) {
        LocalDate today = LocalDate.now(); // ex. 2025-05-20
        LocalDateTime start = today.atStartOfDay();             // 2025-05-20T00:00:00
        LocalDateTime end = today.plusDays(1).atStartOfDay();   // 2025-05-21T00:00:00

        List<Notification> list = notificationRepository
                .findByUserIdAndNotifiedAtBetween(userId, start, end);

        return list.stream()
                .map(NotificationResponse::fromEntity) // DTO 변환
                // .map(Notification -> NotificationResponse.fromEntity(Notification))
                .toList();
    }
}
