package ssafy.project07.repository.badge;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project07.domain.UserBadge;

import java.util.List;

public interface UserBadgeRepository extends JpaRepository<UserBadge, Long> {
    List<UserBadge> findByUserId(Long userId);
}