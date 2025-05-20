package ssafy.project07.dto.column;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ColumnResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String pharmacistName;
    private Long pharmacistId;

}
