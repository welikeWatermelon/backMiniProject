package ssafy.project07.dto.youtube.api;

import lombok.Data;
import ssafy.project07.dto.youtube.YoutubeResponseMini;

import java.util.List;

@Data
public class YoutubeSearchListResponse {
    private List<YoutubeResponseMini> items;
}
