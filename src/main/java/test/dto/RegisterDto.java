package test.dto;

import lombok.Data;
import test.model.Role;

@Data
public class RegisterDto {
    private String login;
    private String password;
    private Role role;
    private String fio;
//    @Email
    private String email;
    private String phone;

}
