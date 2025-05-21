package ssafy.project07.controller;

// 0521 gpt 분석
import ssafy.project07.dto.ai.AnalysisRequest;
import ssafy.project07.dto.ai.AnalysisResponse;
import ssafy.project07.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analysis")
public class AnalysisController {

    @Autowired
    private AnalysisService analysisService;

    @PostMapping
    public AnalysisResponse analyze(@RequestBody AnalysisRequest request) {
        return analysisService.analyzeWithGpt(request);
    }
}