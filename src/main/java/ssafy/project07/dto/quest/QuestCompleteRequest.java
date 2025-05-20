package ssafy.project07.dto.quest;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class QuestCompleteRequest {
    private Long userId;
    private Long questId;
    // completedAt은 서버에서 넣을 수도 있음
}