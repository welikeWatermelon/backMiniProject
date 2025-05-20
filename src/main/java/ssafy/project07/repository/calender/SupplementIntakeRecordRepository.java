package ssafy.project07.repository.calender;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project07.domain.calender.SupplementIntakeRecord;

import java.util.List;

// 특정 날짜(CalendarLog ID)의 섭취 기록 모두 조회
public interface SupplementIntakeRecordRepository extends JpaRepository<SupplementIntakeRecord, Long> {
    List<SupplementIntakeRecord> findByCalendarLogId(Long calendarLogId);
}