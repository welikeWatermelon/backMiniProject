package ssafy.project07.dto.supplement;

import lombok.Data;

import java.time.LocalTime;

@Data
public class AlarmSettingRequest {
    private String message;
    private LocalTime alarmTime;
}