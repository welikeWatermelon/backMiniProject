    package ssafy.project07.service;

    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import ssafy.project07.domain.User;
    import ssafy.project07.domain.Supplement;
    import ssafy.project07.domain.calender.*;
    import ssafy.project07.domain.enums.IntakeTimeType;
    import ssafy.project07.dto.calendar.DailyNutrientStatusDto;
    import ssafy.project07.dto.calendar.DailySummaryResponse;
    import ssafy.project07.dto.calendar.IntakeRecordRequest;
    import ssafy.project07.dto.calendar.SupplementIntakeRecordDto;
    import ssafy.project07.repository.calender.*;

    import java.time.LocalDate;
    import java.util.*;

    @Service
    @RequiredArgsConstructor
    public class CalendarService {

        private final CalendarLogRepository calendarLogRepository;
        private final SupplementRepository supplementRepository;
        private final SupplementIntakeRecordRepository intakeRecordRepository;
        private final DailyRecommendedIntakeRepository recommendedIntakeRepository;

        @Transactional
        // 사용자의 날짜별 캘린더를 조회하거나 새로 만들고,
        // 해당 시간대와 영양제에 대한 섭취 기록을 저장
        public void saveIntakeRecord(Long userId, IntakeRecordRequest request) {

    //        // 영양제 조회 -> 영양제를 미리 저장해서 그걸 가져오는게 아니라 그 상황상황마다 입력해서 저장하는 것이기 때문
    //        Supplement supplement = supplementRepository.findById(request.getSupplementId())
    //                .orElseThrow(() -> new RuntimeException("해당 영양제가 존재하지 않습니다."));

            // 캘린더 조회
            CalendarLog calendarLog = calendarLogRepository.findByUserIdAndDate(userId, request.getDate())
                    .orElseGet(() -> {
                        CalendarLog newLog = new CalendarLog();
                        newLog.setDate(request.getDate());
                        User user = new User(); // 임시 객체로 ID만 설정 -> 굳이 id 아는데, 특정 유저를 조회할 필요가 없음
                        user.setId(userId);
                        newLog.setUser(user);
                        return calendarLogRepository.save(newLog);
                    });

            SupplementIntakeRecord record = new SupplementIntakeRecord();
            record.setCalendarLog(calendarLog);
            record.setSupplementName(request.getSupplementName());
            record.setAmountTakenMg(request.getAmountTakenMg());
//            record.setTimeType(IntakeTimeType.valueOf(request.getIntakeTimeType().toUpperCase()));
            record.setTimeType(IntakeTimeType.valueOf(request.getIntakeTimeType()));
            intakeRecordRepository.save(record);
        }

//        @Transactional(readOnly = true)
//        // 특정 날짜의 기록을 모두 모아서
//        // 영양소별 총합을 권장량과 비교 후 메시지를 생성하여 응답
//        public DailySummaryResponse getDailySummary(Long userId, LocalDate date) {
//            CalendarLog calendarLog = calendarLogRepository.findByUserIdAndDate(userId, date)
//                    .orElseThrow(() -> new RuntimeException("해당 날짜의 복용 기록이 없습니다."));
//
//            List<SupplementIntakeRecord> records = intakeRecordRepository.findByCalendarLogId(calendarLog.getId());
//
//            Map<String, Integer> nutrientTotals = new HashMap<>();
//
//            for (SupplementIntakeRecord record : records) {
//                String name = record.getSupplementName(); // ex. "비타민C"
//                int amount = record.getAmountTakenMg();
//
//                nutrientTotals.put(name,
//                        nutrientTotals.getOrDefault(name, 0) + amount);
//            }
//
//
//            List<DailyNutrientStatusDto> statusList = new ArrayList<>();
//
//            for (Map.Entry<String, Integer> entry : nutrientTotals.entrySet()) {
//                String name = entry.getKey(); //영양제 이름
//                int totalTaken = entry.getValue(); //영양제 섭취량
//
//                int recommended = recommendedIntakeRepository.findByNutrientName(name)
//                        .map(DailyRecommendedIntake::getRecommendedAmountMg)
//                        .orElse(0); // 영양제 권장량
//
//                String message;
//                if (totalTaken > recommended) {
//                    message = name + "를 " + (totalTaken - recommended) + "mg 초과 섭취하셨습니다. 내일은 조금 줄여보아요!";
//                } else if (totalTaken < recommended) {
//                    message = name + "을 " + (recommended - totalTaken) + "mg 덜 섭취하셨습니다. 내일은 조금 더 늘려보아요!";
//                } else {
//                    message = name + " 섭취량이 적절합니다. 잘하고 계십니다!";
//                }
//
//                statusList.add(new DailyNutrientStatusDto(name, totalTaken, recommended, message));
//            }
//
//            return new DailySummaryResponse(date, statusList);
//        }


        @Transactional(readOnly = true)
        public DailySummaryResponse getDailySummary(Long userId, LocalDate date) {
            CalendarLog calendarLog = calendarLogRepository.findByUserIdAndDate(userId, date)
                    .orElseThrow(() -> new RuntimeException("해당 날짜의 복용 기록이 없습니다."));

            // 1. 복용 기록 조회
            List<SupplementIntakeRecord> records = intakeRecordRepository.findByCalendarLogId(calendarLog.getId());

            // 2. 영양소 총합 계산
            Map<String, Integer> nutrientTotals = new HashMap<>();
            for (SupplementIntakeRecord record : records) {
                String name = record.getSupplementName();
                int amount = record.getAmountTakenMg();
                nutrientTotals.put(name, nutrientTotals.getOrDefault(name, 0) + amount);
            }

            // 3. 권장 섭취량과 비교 후 메시지 생성
            List<DailyNutrientStatusDto> statusList = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : nutrientTotals.entrySet()) {
                String name = entry.getKey();
                int totalTaken = entry.getValue();

                int recommended = recommendedIntakeRepository.findByNutrientName(name)
                        .map(DailyRecommendedIntake::getRecommendedAmountMg)
                        .orElse(0);

                String message;
                if (totalTaken > recommended) {
                    message = name + "를 " + (totalTaken - recommended) + "mg 초과 섭취하셨습니다. 내일은 조금 줄여보아요!";
                } else if (totalTaken < recommended) {
                    message = name + "을 " + (recommended - totalTaken) + "mg 덜 섭취하셨습니다. 내일은 조금 더 늘려보아요!";
                } else {
                    message = name + " 섭취량이 적절합니다. 잘하고 계십니다!";
                }

                statusList.add(new DailyNutrientStatusDto(name, totalTaken, recommended, message));
            }

            // ✅ 4. 기록 DTO로 변환
            List<SupplementIntakeRecordDto> recordDtos = records.stream()
                    .map(r -> new SupplementIntakeRecordDto(
                            r.getSupplementName(),
                            r.getAmountTakenMg(),
                            r.getTimeType()))
                    .toList();

            // ✅ 5. 응답 생성
            return new DailySummaryResponse(date, statusList, recordDtos);
        }

    }
