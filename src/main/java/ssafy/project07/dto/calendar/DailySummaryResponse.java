package ssafy.project07.dto.calendar;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
// 클라이언트에 전체 요약 응답 전달
public class DailySummaryResponse {
    private LocalDate date;
    private List<DailyNutrientStatusDto> nutrientStatusList;
    private List<SupplementIntakeRecordDto> intakeRecords; // ✅ 추가 필드
}
