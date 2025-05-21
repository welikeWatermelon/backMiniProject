package ssafy.project07.dto.ai;

// 0521 gpt 분석
import lombok.Data;

@Data
public class AnalysisResponse {
    private String recommendation; // GPT가 추천한 결과 (ex. "비타민B군과 마그네슘이 필요합니다.")
}