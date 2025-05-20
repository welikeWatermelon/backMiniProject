package ssafy.project07.repository.calender;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project07.domain.calender.CalendarLog;

import java.time.LocalDate;
import java.util.Optional;
// 특정 사용자 + 날짜로 CalendarLog 조회
public interface CalendarLogRepository extends JpaRepository<CalendarLog, Long> {
    Optional<CalendarLog> findByUserIdAndDate(Long userId, LocalDate date);
}
