package ssafy.project07.dto.column;

// 추가 코드 0521

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PharmacistColumnListResponse {
    private String pharmacistName;
    private List<ColumnSummaryDto> columns;
}
