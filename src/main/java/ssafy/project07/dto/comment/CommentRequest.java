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


}
