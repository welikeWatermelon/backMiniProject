package ssafy.project07.service;

import ssafy.project07.dto.youtube.YoutubeRealResponse;
import ssafy.project07.dto.youtube.YoutubeResponseMini;
import ssafy.project07.dto.youtube.api.YoutubeSearchListResponse;
import ssafy.project07.repository.youtube.YoutubeVideoRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class YoutubeService {

    // application.properties 에 입력한 api.key 값이 들어감
    @Value("${youtube.api.key}")
    private String apiKey;

    @Autowired
    private YoutubeVideoRepository youtubeVideoRepository;


    @Autowired
    private RestTemplate restTemplate;

    public List<YoutubeRealResponse> fetchTop10Videos() {
        String query = "영양제";
        // 상위 50개 가져오기
        String url = String.format(
                "https://www.googleapis.com/youtube/v3/search?part=snippet&q=%s&type=video&maxResults=10&order=viewCount&regionCode=KR&relevanceLanguage=ko&key=%s",
                query, apiKey
        );

        ResponseEntity<YoutubeSearchListResponse> response =
                restTemplate.getForEntity(url, YoutubeSearchListResponse.class);

        List<YoutubeResponseMini> items = response.getBody().getItems();

        // 1. 50개 중 가공
        List<YoutubeRealResponse> allVideos = items.stream()
                .map(item -> {
                    String videoId = item.getId().getVideoId();
                    String thumbnailUrl = item.getSnippet().getThumbnails().getMedium().getUrl();
                    String youtubeUrl = "https://www.youtube.com/watch?v=" + videoId;
                    return new YoutubeRealResponse(youtubeUrl, thumbnailUrl);
                })
                .collect(Collectors.toList());

        // 2. 랜덤 셔플 후 앞에서 10개만 선택
        Collections.shuffle(allVideos);
        return allVideos.subList(0, Math.min(4, allVideos.size())); // 만약 50개 안 될 경우 대비
    }
}