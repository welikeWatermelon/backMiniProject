package ssafy.project07.domain.community;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import ssafy.project07.domain.user.User;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment {
    @Id @GeneratedValue
    private Long id;
    private String content;

    @CreatedDate // 공부 필요
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private CommunityPost communityPost;
}