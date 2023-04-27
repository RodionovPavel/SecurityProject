package test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
@AllArgsConstructor
public class ClientOtpRequest {
    @NotBlank
    private String fullOperationId;

    @NotBlank
    private String otpKey;
}
