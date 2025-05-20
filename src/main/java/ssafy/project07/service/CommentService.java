package ssafy.project07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.project07.domain.community.Comment;
import ssafy.project07.domain.community.CommunityPost;
import ssafy.project07.domain.user.User;
import ssafy.project07.dto.comment.CommentRequest;
import ssafy.project07.dto.comment.CommentResponse;
import ssafy.project07.repository.comment.CommentRepository;
import ssafy.project07.repository.community.CommunityRepository;
import ssafy.project07.repository.user.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;



    public List<CommentResponse> getCommentsByPostId(Long postId) {
        return commentRepository.findByCommunityPostId(postId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }



    public Long save(User user, Long postId, CommentRequest request) {

        CommunityPost post = communityRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUser(user); // 인증된 사용자 주입
        comment.setCommunityPost(post);

        return commentRepository.save(comment).getId();
    }



    public void delete(User user, Long postId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new SecurityException("댓글 작성자만 삭제할 수 있습니다.");
        }

        if (!comment.getCommunityPost().getId().equals(postId)) {
            throw new IllegalArgumentException("게시글과 댓글이 일치하지 않습니다.");
        }

        commentRepository.delete(comment);
    }

    private CommentResponse convertToResponse(Comment comment) {
        CommentResponse response = new CommentResponse();
        response.setId(comment.getId());
        response.setContent(comment.getContent());
        response.setAuthorName(comment.getUser().getName()); // User의 이름 가져오기
        response.setCreatedAt(comment.getCreatedAt());
        return response;
    }

}
