package test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class OtpData {

    private UUID operationId;

    private String login;

    private long ttlMinutes;

    private String otpCode;

}
