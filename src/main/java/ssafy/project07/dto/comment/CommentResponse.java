package ssafy.project07.dto.comment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponse {
    private Long id;                // 댓글 ID
    private String content;         // 댓글 내용
    private String authorName;      // 작성자 이름
    private LocalDateTime createdAt; // 작성 시간
}
