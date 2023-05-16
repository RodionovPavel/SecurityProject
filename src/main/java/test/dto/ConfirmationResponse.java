package test.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConfirmationResponse {

    private boolean result;

    private String message;

    private String token;

}
