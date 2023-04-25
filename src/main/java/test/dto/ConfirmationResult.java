package test.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ConfirmationResult {

    private UUID clientId;

    private boolean result;

    private String message;

}
