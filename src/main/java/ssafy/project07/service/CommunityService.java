package ssafy.project07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.project07.domain.CommunityPost;
import ssafy.project07.domain.User;
import ssafy.project07.dto.community.CommunityRequest;
import ssafy.project07.dto.community.CommunityResponse;
import ssafy.project07.repository.community.CommunityRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;

    // 모든 게시글 보기
    public List<CommunityResponse> getAllPosts(String sort) {
        List<CommunityPost> posts = communityRepository.findAll(); // sort 조건은 추후 정렬 로직 추가 가능

        if ("recent".equals(sort)) { // 최신순
            posts = communityRepository.findAllByOrderByCreatedAtDesc();
        } else if ("title".equals(sort)) { //제목순
            posts = communityRepository.findAllByOrderByTitleAsc();
        }
        return posts.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    // 특정 작성자의 게시글 보기 (상세보기)
    public CommunityResponse getPostById(Long id) {
        CommunityPost post = communityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        return convertToResponse(post);
    }

    // 작성
    public Long createPost(CommunityRequest request, User user) {
        CommunityPost post = new CommunityPost();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        post.setUser(user);
        post.setAuthorName(user.getName()); // ✅ 추가!

        return communityRepository.save(post).getId();
    }

    // 수정
    public void updatePost(Long id, CommunityRequest request, User user) {
        CommunityPost post = communityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        if (!post.getUser().getId().equals(user.getId())) {
            throw new IllegalStateException("작성자만 수정할 수 있습니다.");
        }

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setUpdatedAt(LocalDateTime.now());
        communityRepository.save(post);
    }

    // 삭제
    public void deletePost(Long id, User user) {
        CommunityPost post = communityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        if (!post.getUser().getId().equals(user.getId())) {
            throw new IllegalStateException("작성자만 삭제할 수 있습니다.");
        }
        communityRepository.deleteById(id);
    }



    // 모든 게시글 정보에서 보기 쉽게 응답으로 바꾸는 것
    private CommunityResponse convertToResponse(CommunityPost post) {
        CommunityResponse res = new CommunityResponse();
        res.setId(post.getId());
        res.setTitle(post.getTitle());
        res.setContent(post.getContent());
        res.setCreatedAt(post.getCreatedAt());
        res.setUpdatedAt(post.getUpdatedAt());
        res.setAuthorName(post.getUser().getName()); // 필요시
        return res;
    }
}
