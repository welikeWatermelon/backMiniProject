package ssafy.project07.repository.badge;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project07.domain.badge.Badge;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
}