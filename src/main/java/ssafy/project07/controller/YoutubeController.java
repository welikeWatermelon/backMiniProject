package ssafy.project07.controller;

import ssafy.project07.dto.youtube.YoutubeRealResponse;
import ssafy.project07.service.YoutubeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/youtube")
public class YoutubeController {

    @Autowired
    private YoutubeService youtubeService;


    @GetMapping("/top10")
    public ResponseEntity<List<YoutubeRealResponse>> getTop10Videos() {
        List<YoutubeRealResponse> videos = youtubeService.fetchTop10Videos();
        return ResponseEntity.ok(videos);
    }

}