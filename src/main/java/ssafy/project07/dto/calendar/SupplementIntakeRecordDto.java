package ssafy.project07.dto.calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import ssafy.project07.domain.enums.IntakeTimeType;

@Data
@AllArgsConstructor
public class SupplementIntakeRecordDto {
    private String supplementName;
    private int amountTakenMg;
    private IntakeTimeType timeType;
}
