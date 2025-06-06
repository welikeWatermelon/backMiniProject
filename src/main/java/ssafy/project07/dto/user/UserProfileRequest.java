package ssafy.project07.dto.user;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
public class UserProfileRequest {
    private String name;
    private LocalDate birthDate;
    private String gender;
    private String nickname;
    private String profileImage;

}
