package ssafy.project07.dto.comment;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class CommentRequest {
    private String content;     // 댓글 본문
//    private Long userId;        // 댓글 작성자 ID
//    private Long postId;        // 댓글이 달릴 게시글 ID
    // createdAt은 DTO에서 굳이 받을 필요 없음 → @CreatedDate로 자동 생성됨

}
