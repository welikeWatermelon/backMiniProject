package ssafy.project07.domain.calender;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
// 영양소별 하루 권장 섭취량 정보 저장
public class DailyRecommendedIntake {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "nutrient_name")
    private Nutrient nutrient;

    private int recommendedAmountMg;
}
