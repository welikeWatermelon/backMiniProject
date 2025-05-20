package ssafy.project07.dto.calendar;

import lombok.Data;
import java.time.LocalDate;

@Data
// 클라이언트에서 기록 저장 요청 시 사용하는 입력값
public class IntakeRecordRequest {
    private String supplementName;           // ex. "비타민C"
    private int amountTakenMg;               // ex. 300
    private LocalDate date;
    private String intakeTimeType;           // MORNING, LUNCH 등
}

