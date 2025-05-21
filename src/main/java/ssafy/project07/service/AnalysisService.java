package ssafy.project07.service;

// 0521 gpt 분석
import org.springframework.beans.factory.annotation.Value;
import ssafy.project07.dto.ai.AnalysisRequest;
import ssafy.project07.dto.ai.AnalysisResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.*;

@Service
public class AnalysisService {

    private final String GPT_API_URL = "https://api.openai.com/v1/chat/completions";

    @Value("${gpt.api.Key}") // application.properties에서 불러오기
    // 설정해야함
    private String apiKey;

    public AnalysisResponse analyzeWithGpt(AnalysisRequest request) {
        try {// 1. GPT에게 보낼 메시지 구성

            List<Map<String, String>> messages = new ArrayList<>();
            messages.add(Map.of("role", "system", "content", "너는 영양제 전문가야."));
            messages.add(Map.of("role", "user", "content", "다음은 사용자 증상들이야:\n" + String.join(", ", request.getAnswers())));

            // 2. 요청 바디 구성
            Map<String, Object> body = Map.of(
                    "model", "gpt-3.5-turbo",
                    "messages", messages
            );

            // 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + apiKey);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

            RestTemplate restTemplate = new RestTemplate();

            // 요청 전송
            ResponseEntity<Map> response = restTemplate.postForEntity(GPT_API_URL, entity, Map.class);

            // 4. 결과 파싱
            String reply = ((Map) ((List) response.getBody().get("choices")).get(0)).get("message").toString();

//        // 결과 파싱 (2번)
//        Map message = (Map) ((Map) ((List) response.getBody().get("choices")).get(0)).get("message");
//        String reply = (String) message.get("content");

            // 5. 반환
            AnalysisResponse res = new AnalysisResponse();
            res.setRecommendation(reply);
            return res;
        } catch (Exception e) {
            // 오류 발생 시 fallback 메시지
            AnalysisResponse errorRes = new AnalysisResponse();
            errorRes.setRecommendation("GPT 응답 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
            return errorRes;
        }
    }
}
