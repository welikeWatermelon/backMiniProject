package ssafy.project07.repository.calender;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project07.domain.Supplement;

// 영양제 ID로 Supplement 조회
public interface SupplementRepository extends JpaRepository<Supplement, Long> {
}