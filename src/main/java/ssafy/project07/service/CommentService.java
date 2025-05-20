package ssafy.project07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.project07.domain.Comment;
import ssafy.project07.domain.CommunityPost;
import ssafy.project07.domain.User;
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

//    public List<Comment> getCommentsByPostId(Long postId) {
//        return commentRepository.findByCommunityPostId(postId);
//    }

    public List<CommentResponse> getCommentsByPostId(Long postId) {
        return commentRepository.findByCommunityPostId(postId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

//    public Long save(CommentRequest request) {
//
//        // 1. 유저 조회
//        User user = userRepository.findById(request.getUserId())
//                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
//
//        // 2. 게시글 조회
//        CommunityPost post = communityRepository.findById(request.getPostId())
//                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
//
//        // 3. 댓글 생성
//        Comment comment = new Comment();
//        comment.setContent(request.getContent());
//        comment.setCreatedAt(LocalDateTime.now()); // 또는 JPA Auditing으로 자동 처리
//        comment.setUser(user);
//        comment.setCommunityPost(post);
//
//        // 4. 저장 및 ID 반환
//        return commentRepository.save(comment).getId();
//    }


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


//    public void delete(Long commentId) {
//        Comment comment = commentRepository.findById(commentId)
//                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
//        commentRepository.delete(comment);
//    }

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
