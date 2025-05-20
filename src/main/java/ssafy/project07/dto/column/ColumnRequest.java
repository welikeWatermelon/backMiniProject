package ssafy.project07.dto.column;

import lombok.Data;

@Data
public class ColumnRequest {
    private String title;
    private String content;
    private Long pharmacistId;
}