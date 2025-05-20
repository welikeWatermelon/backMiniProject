package ssafy.project07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.project07.domain.Quest;
import ssafy.project07.domain.QuestHistory;
import ssafy.project07.domain.User;
import ssafy.project07.dto.quest.QuestCompleteRequest;
import ssafy.project07.repository.quest.QuestHistoryRepository;
import ssafy.project07.repository.quest.QuestRepository;
import ssafy.project07.repository.user.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestService {
    private final QuestRepository questRepository;
    private final UserRepository userRepository;
    private final QuestHistoryRepository questHistoryRepository;

    public Quest getQuest() {
        List<Quest> quests = questRepository.findAll();

        if (quests.isEmpty()) {
            throw new IllegalStateException("등록된 퀘스트가 없습니다.");
        }

        int todayIndex = LocalDate.now().getDayOfYear() % quests.size();

        return quests.get(todayIndex);
    }

    public void completeQuest(QuestCompleteRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        Quest quest = questRepository.findById(request.getQuestId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 퀘스트입니다."));

        QuestHistory history = new QuestHistory();
        history.setUser(user);
        history.setQuest(quest);
        history.setCompletedAt(LocalDateTime.now());

        questHistoryRepository.save(history);
    }

    public List<QuestHistory> historyListService(Long userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException("에러 !"));
//
//        List<QuestHistory> questHistoryList = user.getQuestHistories();
//        return questHistoryList;

        return questHistoryRepository.findByUserId(userId); // 안정적
    }


    public boolean isQuestCompletedToday(Long userId) {
        // 오늘 날짜 기준으로 퀘스트 기록이 있는지 확인
        LocalDate today = LocalDate.now();
        return questHistoryRepository.existsByUserIdAndCompletedAtBetween(
                userId,
                today.atStartOfDay(),
                today.plusDays(1).atStartOfDay()
        );
    }
}
