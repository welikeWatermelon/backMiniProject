package ssafy.project07.repository.calender;


import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project07.domain.calender.Nutrient;

import java.util.Optional;

public interface NutrientRepository extends JpaRepository<Nutrient, Long> {
    Optional<Nutrient> findByName(String name);
}
