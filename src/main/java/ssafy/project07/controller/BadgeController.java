package ssafy.project07.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.project07.dto.badge.BadgeResponse;
import ssafy.project07.service.BadgeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/badges")
public class BadgeController {

    private final BadgeService badgeService;

    @GetMapping("/all")
    public ResponseEntity<List<BadgeResponse>> getAllBadges() {
        return ResponseEntity.ok(badgeService.getAllBadges());
    }

    @GetMapping("/my")
    public ResponseEntity<List<BadgeResponse>> getMyBadges(@RequestParam Long userId) {
        return ResponseEntity.ok(badgeService.getUserBadges(userId));
    }
}
