package test.service;

import test.dto.ConfirmationResponse;
import test.dto.ConfirmationResult;
import test.dto.OtpData;

import java.util.UUID;

public interface OtpService {

    OtpData generate(UUID clientId);

    ConfirmationResult check(UUID operationId, String otp);

}
