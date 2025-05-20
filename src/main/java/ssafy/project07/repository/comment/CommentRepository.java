package ssafy.project07.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project07.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByCommunityPostId(Long postId);

}

