package ssafy.project07.repository.calender;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project07.domain.calender.DailyRecommendedIntake;

import java.util.Optional;

// 영양소 이름으로 권장량 조회
public interface DailyRecommendedIntakeRepository extends JpaRepository<DailyRecommendedIntake, Long> {
    Optional<DailyRecommendedIntake> findByNutrientName(String nutrientName);

}
