package ssafy.project07.dto.user;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String email;
    private String password;
}
