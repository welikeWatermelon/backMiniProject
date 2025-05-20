package ssafy.project07.repository.pharmacist;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project07.domain.Pharmacist;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {
}
