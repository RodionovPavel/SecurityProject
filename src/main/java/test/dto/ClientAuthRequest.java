package test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ClientAuthRequest {

    @NotBlank
    private String login;

    @NotBlank
    private String password;

}
