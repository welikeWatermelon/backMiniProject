package ssafy.project07.dto.calendar;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
// 하루 섭취 요약 응답 내의 "한 영양소"에 대한 상태 정보
public class DailyNutrientStatusDto {
    private String nutrientName;     // 예: 비타민C
    private int totalTakenMg;        // 오늘 섭취량 총합
    private int recommendedMg;       // 권장 섭취량
    private String message;          // "비타민C를 40mg 초과 섭취하셨습니다"
}
