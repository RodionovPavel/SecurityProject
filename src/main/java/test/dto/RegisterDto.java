package test.dto;

import lombok.Data;
import test.model.Role;

import java.util.UUID;

@Data
public class RegisterDto {
    private UUID id;
    private String login;
    private String password;
    private Role role;
    private String fio;
    private String email;
    private String phone;

}
