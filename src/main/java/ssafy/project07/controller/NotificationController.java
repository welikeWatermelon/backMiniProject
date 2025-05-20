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

    @PostMapping("/read/{id}")
    public ResponseEntity<Void> readNotification(@PathVariable Long id) {
        notificationService.markAsRead(id);
        return ResponseEntity.ok().build();
    }
}
