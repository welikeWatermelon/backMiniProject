package ssafy.project07.dto.user;

import jakarta.persistence.Entity;
import jdk.jfr.Enabled;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
public class UserProfileResponse {
    private String name;
    private String email;
    private LocalDate birthDate;
    private String gender;
    private String nickname;
    private String profileImage;

}
