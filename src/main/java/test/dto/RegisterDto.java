package test.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import test.model.Role;

import java.util.UUID;

@Data
@Schema(description = "Сущность пользователя")
public class RegisterDto {
    @Schema(description = "Идентификатор в UUID", example = "bc920467-6575-4ef6-a46a-ad8fde11c6af", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @Schema(required = true, description = "Логин", example = "vasya", minLength = 3)
    private String login;

    @Schema(required = true, description = "Пароль", example = "12345")
    private String password;

    @Schema(required = true, description = "Роль пользователя", example = "USER")
    private Role role;

    @Schema(required = true, description = "Фамилия Имя Отчество")
    private String fio;

    @Schema(required = true, description = "Email")
    private String email;

    @Schema(required = true, description = "Телефон")
    private String phone;

}
