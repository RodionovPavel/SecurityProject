package test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class OtpData {

    private UUID operationId;

    private UUID clientId;

    private long ttlMinutes;

    private String otpCode;

}
