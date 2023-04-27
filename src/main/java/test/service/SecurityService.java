package test.service;

import test.dto.ClientRegisterRequest;
import test.dto.ConfirmationResponse;
import test.dto.JwtResponse;
import test.dto.OtpResponse;

import java.util.UUID;

public interface SecurityService {

    OtpResponse register(ClientRegisterRequest registerDto);

    JwtResponse auth(String login, String password);

    ConfirmationResponse confirm(UUID operationId, String otpCode);

}
