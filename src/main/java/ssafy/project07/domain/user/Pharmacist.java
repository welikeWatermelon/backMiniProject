package ssafy.project07.domain.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ssafy.project07.domain.follow.Follow;
import ssafy.project07.domain.column.Column;

import java.util.List;


@Entity
@Getter
@Setter
public class Pharmacist {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String title;
    private String profileImage;

    private String licenseNumber; // 약사 면허 번호
    private String hospitalName;  // 근무 병원명

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "pharmacist")
    private List<Column> columns;

    @OneToMany(mappedBy = "pharmacist")
    private List<Follow> followers;



}
