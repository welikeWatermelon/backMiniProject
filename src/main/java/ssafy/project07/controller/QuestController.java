package ssafy.project07.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ssafy.project07.domain.quest.Quest;
import ssafy.project07.domain.quest.QuestHistory;
import ssafy.project07.dto.quest.QuestCompleteRequest;
import ssafy.project07.service.QuestService;

import java.util.List;

@RestController
@RequestMapping("/api/quests")
@RequiredArgsConstructor
public class QuestController {

    private final QuestService questService;

    @GetMapping("/today")
    public Quest todayQuest() {
        return questService.getQuest();
    }

    @GetMapping("/today/completed")
    public boolean isTodayQuestCompleted(@RequestParam Long userId) {
        return questService.isQuestCompletedToday(userId);
    }

    @PostMapping("/complete")
    public void complete(@RequestBody QuestCompleteRequest request) {
        questService.completeQuest(request);
    }

    @GetMapping("/history")
    public List<QuestHistory> historyList(@RequestParam Long userId) {
        return questService.historyListService(userId);
    }

}
