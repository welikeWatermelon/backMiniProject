package ssafy.project07.dto.user;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import ssafy.project07.domain.enums.Gender;
import ssafy.project07.domain.enums.UserRole;

import java.time.LocalDate;

@Data
public class UserRegisterRequest {
    private String name;
    private String email;
    private String password;
    private LocalDate birthDate;
    private String gender; // MALE or FEMALE
    private String nickname;
    private String profileImage;
    private String role; // USER or PHARMACIST

    // 약사 전용 필드 (선택)
    private String licenseNumber;
    private String hospitalName;
}
