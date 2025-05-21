package ssafy.project07.dto.ai;

// 0521 gpt 분석
import lombok.Data;
import java.util.List;

@Data
public class AnalysisRequest {
    private List<String> answers;  // 각 질문에 대한 답변들
}