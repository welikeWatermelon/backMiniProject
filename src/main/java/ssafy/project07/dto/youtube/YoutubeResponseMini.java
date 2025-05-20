package ssafy.project07.dto.youtube;

import lombok.Data;

@Data
public class YoutubeResponseMini {
    private Id id;
    private Snippet snippet;

    @Data
    public static class Id {
        private String videoId;
    }

    @Data
    public static class Snippet {
        private Thumbnails thumbnails;

        @Data
        public static class Thumbnails {
            private Thumbnail medium;

            @Data
            public static class Thumbnail {
                private String url;
            }
        }
    }
}
