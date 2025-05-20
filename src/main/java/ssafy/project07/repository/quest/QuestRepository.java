package ssafy.project07.repository.quest;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project07.domain.Quest;

public interface QuestRepository extends JpaRepository<Quest, Long> {

}
