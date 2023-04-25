package test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class OtpResponse {

    private UUID operationId;

    private long ttlMinutes;

}
