package ssafy.project07.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.project07.dto.notification.NotificationRequest;
import ssafy.project07.dto.notification.NotificationResponse;
import ssafy.project07.service.NotificationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getMyNotifications(@RequestParam Long userId) {
        return ResponseEntity.ok(notificationService.getUserNotifications(userId));
    }

    @PostMapping
    public ResponseEntity<Void> registerNotification(@RequestBody NotificationRequest request,
                                                     @RequestParam Long userId) {
        notificationService.registerNotification(userId, request);
        return ResponseEntity.ok().build();
    }

    // 0520 추가 - notification 목록을 택했을 때, 오늘 알림들이 나오는 것
    @GetMapping("/today")
    public ResponseEntity<List<NotificationResponse>> getTodayNotifications(@RequestParam Long userId) {
        return ResponseEntity.ok(notificationService.getTodayNotifications(userId));
    }

    @PostMapping("/read/{id}")
    public ResponseEntity<Void> readNotification(@PathVariable Long id) {
        notificationService.markAsRead(id);  // ✅ 읽음 처리 서비스 호출
        return ResponseEntity.ok().build();
    }
}
