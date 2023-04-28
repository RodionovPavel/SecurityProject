package test.service;

import test.dto.ClientRegisterRequest;
import test.dto.ConfirmationResponse;
import test.dto.ConfirmationResult;
import test.dto.OtpData;

import java.util.UUID;

public interface OtpService {

    OtpData generate(ClientRegisterRequest request);

    ConfirmationResult check(UUID operationId, String otp);

}
