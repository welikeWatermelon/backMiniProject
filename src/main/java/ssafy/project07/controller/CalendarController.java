package ssafy.project07.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ssafy.project07.domain.User;
import ssafy.project07.dto.calendar.IntakeRecordRequest;
import ssafy.project07.dto.calendar.DailySummaryResponse;
import ssafy.project07.service.CalendarService;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    /**
     * 복용 기록 저장
     */
    @PostMapping("/intake")
    public ResponseEntity<String> saveIntake(@RequestParam Long userId,
                                             @RequestBody IntakeRecordRequest request,
                                             @AuthenticationPrincipal User user) {
        calendarService.saveIntakeRecord(userId, request);
        return ResponseEntity.ok("복용 기록이 저장되었습니다.");
    }

    /**
     * 하루 요약 조회
     */
    @GetMapping("/summary")
    public ResponseEntity<DailySummaryResponse> getDailySummary(@RequestParam Long userId,
                                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                                @AuthenticationPrincipal User user) {
        DailySummaryResponse summary = calendarService.getDailySummary(userId, date);
        return ResponseEntity.ok(summary);
    }
}
