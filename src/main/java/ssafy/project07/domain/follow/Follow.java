package ssafy.project07.domain.follow;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ssafy.project07.domain.user.Pharmacist;
import ssafy.project07.domain.user.User;

@Entity
@Getter
@Setter
@Table(
        name = "follow",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "pharmacist_id"})
        }
)
// N:M 가운데 지점
public class Follow {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "pharmacist_id", nullable = false)
    private Pharmacist pharmacist;
}