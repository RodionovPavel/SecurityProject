package test.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ConfirmationResult {

    private String login;

    private boolean result;

    private String message;

    private String password;

    private String fullName;

    private String email;

    private String phone;

}
