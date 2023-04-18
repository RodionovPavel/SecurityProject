package test.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Сущность пользователя")
public class ClientRegisterRequest {

    @NotBlank
    @Size(min = 5, max = 10)
    @Schema(description = "Логин", example = "vasya")
    private String login;

    @NotBlank
    @Size(min = 7, max = 14)
    @Schema(description = "Пароль", example = "password!")
    private String password;

    @NotBlank
    @Size(min = 3, max = 40)
    @Schema(description = "Фамилия Имя Отчество")
    private String fullName;

    @Email
    @NotBlank
    @Schema(description = "Почта пользователя")
    private String email;

    @Pattern(regexp = "^7[0-9]{10}$")
    @Schema(description = "Номер телефона", example = "79998887766")
    private String phone;

}
