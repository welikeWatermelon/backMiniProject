package ssafy.project07.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ssafy.project07.domain.Comment;
import ssafy.project07.domain.User;
import ssafy.project07.dto.comment.CommentRequest;
import ssafy.project07.dto.comment.CommentResponse;
import ssafy.project07.dto.community.CommunityRequest;
import ssafy.project07.dto.community.CommunityResponse;
import ssafy.project07.service.CommentService;
import ssafy.project07.service.CommunityService;

import java.util.List;

@RestController
@RequestMapping("/api/community/posts")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;
    private final CommentService commentService;

    // 게시글 조회
    @GetMapping
    public ResponseEntity<List<CommunityResponse>> getAllPosts(@RequestParam(required = false) String sort) {
        return ResponseEntity.ok(communityService.getAllPosts(sort));
    }

    // 특정 게시글 조회
    @GetMapping("/{id}")
    public ResponseEntity<CommunityResponse> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(communityService.getPostById(id));
    }

    // 게시글 등록
    @PostMapping
    public ResponseEntity<Long> createPost(@RequestBody CommunityRequest request,
                                           @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(communityService.createPost(request, user));
    }

    // 수정
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePost(@PathVariable Long id,
                                           @RequestBody CommunityRequest request,
                                           @AuthenticationPrincipal User user) {
        communityService.updatePost(id, request, user);
        return ResponseEntity.ok().build();
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id,
                                           @AuthenticationPrincipal User user) {
        // 지금 로그인한게 누군지 아는 것 : @AuthenticationPrincipal
        communityService.deletePost(id,user);
        return ResponseEntity.ok().build();
    }

    // 댓글 달기
    @PostMapping("/comments/{postId}")
    public ResponseEntity<Long> writeComment(@PathVariable Long postId,
                                             @RequestBody CommentRequest request,
                                             @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(commentService.save(user, postId, request));
    }

    // 댓글 리스트 얻기
    @GetMapping("/comments/{postId}")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{postId}/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long postId, //
                                              @PathVariable Long commentId,
                                              @AuthenticationPrincipal User user) {
        commentService.delete(user, postId, commentId);
        return ResponseEntity.ok().build();
    }
}
