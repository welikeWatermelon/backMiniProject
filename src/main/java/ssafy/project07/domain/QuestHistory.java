package ssafy.project07.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class QuestHistory {
    @Id @GeneratedValue
    private Long id;
    private LocalDateTime completedAt;

    @ManyToOne
    private User user;

    @ManyToOne
    private Quest quest;
}