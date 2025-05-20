package ssafy.project07.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Quest {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private int point;

    @OneToMany(mappedBy = "quest")
    private List<QuestHistory> questHistories;
}