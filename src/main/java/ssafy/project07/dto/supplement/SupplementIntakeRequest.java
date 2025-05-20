package ssafy.project07.dto.supplement;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class SupplementIntakeRequest {
    private Long supplementId; // 영양제 정보
    private int amountTaken; // 복용 양
    private LocalDateTime intakeTime; // 복용 시간
}
