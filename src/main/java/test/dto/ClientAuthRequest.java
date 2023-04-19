package test.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientAuthRequest {

    @NotBlank
    private String login;

    @NotBlank
    private String password;

}
