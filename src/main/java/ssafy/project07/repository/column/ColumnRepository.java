package ssafy.project07.repository.column;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project07.domain.Column;

import java.util.List;

public interface ColumnRepository extends JpaRepository<Column, Long> {
    List<Column> findByPharmacistId(Long pharmacistId);
}
