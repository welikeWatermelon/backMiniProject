package ssafy.project07.repository.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project07.domain.Follow;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByUserId(Long userId);
    // List<Follow> findByUser(Long userId); 이렇게 쓰면 안됨
    // User필드의 Id를 조회해야하니까 !
    // UserId 로 쓰면 user필드의 id를 조회한다는 것임
}
