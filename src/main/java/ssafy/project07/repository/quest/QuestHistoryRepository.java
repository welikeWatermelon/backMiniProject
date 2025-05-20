package ssafy.project07.repository.quest;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project07.domain.QuestHistory;

import java.time.LocalDateTime;
import java.util.List;

public interface QuestHistoryRepository extends JpaRepository<QuestHistory, Long> {
    //해당 userId를 갖고있는 questHistory를 다 찾아서 list에 담아주는거
    List<QuestHistory> findByUserId(Long userId);
    boolean existsByUserIdAndCompletedAtBetween(Long userId, LocalDateTime start, LocalDateTime end);

}
