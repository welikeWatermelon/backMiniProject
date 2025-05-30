package ssafy.project07.repository.community;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project07.domain.community.CommunityPost;

import java.util.List;

public interface CommunityRepository extends JpaRepository<CommunityPost, Long> {

    List<CommunityPost> findAllByOrderByCreatedAtDesc();

    List<CommunityPost> findAllByOrderByTitleAsc();

    List<CommunityPost> findAllByOrderByViewCountDesc(); // 🔥 조회수순 정렬
}
