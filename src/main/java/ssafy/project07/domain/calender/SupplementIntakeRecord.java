package ssafy.project07.domain.calender;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ssafy.project07.domain.enums.IntakeTimeType;

@Entity
@Getter
@Setter
public class SupplementIntakeRecord {

    @Id
    @GeneratedValue
    private Long id;

    private String supplementName;      // 직접 입력한 이름 (ex. 비타민C)
    private int amountTakenMg;          // 복용량

    @Enumerated(EnumType.STRING)
    private IntakeTimeType timeType;    // 아침/점심/저녁/기타

    @ManyToOne
    private CalendarLog calendarLog;
}
