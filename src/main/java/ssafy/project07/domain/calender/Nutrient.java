package ssafy.project07.domain.calender;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
// 영양소 정의 (ex. 비타민C, 마그네슘)
public class Nutrient {
    @Id @GeneratedValue
    private Long id;

    private String name;
}
