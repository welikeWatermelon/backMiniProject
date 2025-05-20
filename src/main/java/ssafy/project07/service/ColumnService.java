package ssafy.project07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.project07.domain.Column;
import ssafy.project07.domain.Pharmacist;
import ssafy.project07.domain.User;
import ssafy.project07.dto.column.ColumnRequest;
import ssafy.project07.dto.column.ColumnResponse;
import ssafy.project07.repository.column.ColumnRepository;
import ssafy.project07.repository.pharmacist.PharmacistRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
// 약사와 관련된 기능들
public class ColumnService {

    private final ColumnRepository columnRepository;
    private final PharmacistRepository pharmacistRepository;

    // 특정 약사의 column 리스트 조회
    public List<ColumnResponse> getColumnsByPharmacist(Long pharmacistId) {
        // 1. 해당 ID의 약사를 찾습니다.
        Pharmacist pharmacist = pharmacistRepository.findById(pharmacistId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 약사입니다."));

        // 2. 그 약사가 쓴 칼럼들을 꺼냅니다.
        List<Column> columnList = pharmacist.getColumns();

        // 3. 응답용 DTO로 하나씩 변환합니다.
        List<ColumnResponse> result = new ArrayList<>();
        for (Column column : columnList) {
            ColumnResponse res = new ColumnResponse();
            res.setId(column.getId());
            res.setTitle(column.getTitle());
            res.setContent(column.getContent());
            res.setCreatedAt(column.getCreatedAt());
            res.setPharmacistName(pharmacist.getName()); //  부분은 프론트에서 작성자 표시용
            result.add(res);
        }

        return result;
    }

    // 칼럼 상세 조회
    public ColumnResponse getColumnById(Long id) {
        Column column = columnRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 칼럼입니다."));

        ColumnResponse res = new ColumnResponse();
        res.setId(column.getId());
        res.setTitle(column.getTitle());
        res.setContent(column.getContent());
        res.setCreatedAt(column.getCreatedAt());

        // 칼럼 작성자 이름 포함
        res.setPharmacistName(column.getPharmacist().getName());

        return res;

    }

    //  칼럼 등록
    public Long create(ColumnRequest request, User user) {
        // request -> Column형식으로 바꿔야함
        // 그 뒤 save 해야함

        // 1. 약사 ID로 약사 조회
        Pharmacist pharmacist = user.getPharmacist();
        if (pharmacist == null) {
            throw new IllegalStateException("약사 프로필이 존재하지 않습니다.");
        }

        // 2. 칼럼 객체 생성
        Column column = new Column();
        column.setTitle(request.getTitle());
        column.setContent(request.getContent());
        column.setCreatedAt(LocalDateTime.now());
        column.setPharmacist(pharmacist);

        // 3. 저장 후 ID 반환
        return columnRepository.save(column).getId();
    }

    // 칼럼 수정
    public void update(Long columnId, ColumnRequest request, User user) {
        // request -> Column 형식으로 바꿔야함
        // 그 뒤 update 해야함

        Column column = columnRepository.
                findById(columnId).
                orElseThrow(() -> new IllegalArgumentException("존재하지 않는 칼럼입니다."));

        // 작성자 본인인지 확인
        if (!column.getPharmacist().getUser().getId().equals(user.getId())) {
            throw new IllegalStateException("칼럼 작성자만 수정할 수 있습니다.");
        }

        // 수정
        column.setTitle(request.getTitle());
        column.setContent(request.getContent());

        // 업데이트
        columnRepository.save(column);

    }

    public void delete(Long columnId, User user) {

        Column column = columnRepository.
                findById(columnId).
                orElseThrow(() -> new IllegalArgumentException("존재하지 않는 칼럼입니다."));

        if (!column.getPharmacist().getUser().getId().equals(user.getId())) {
            throw new IllegalStateException("칼럼 작성자만 삭제할 수 있습니다.");
        }

        columnRepository.deleteById(columnId);
    }

    public List<ColumnResponse> getAllColumns() {
        List<Column> columns = columnRepository.findAll();
        return columns.stream().map(column -> {
            ColumnResponse res = new ColumnResponse();
            res.setId(column.getId());
            res.setTitle(column.getTitle());
            res.setContent(column.getContent());
            res.setCreatedAt(column.getCreatedAt());
            res.setPharmacistName(column.getPharmacist().getName());
            res.setPharmacistId(column.getPharmacist().getId());
            return res;
        }).collect(Collectors.toList());
    }

}
