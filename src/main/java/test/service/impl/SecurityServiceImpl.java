package test.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import test.config.security.JwtProvider;
import test.dto.ClientRegisterRequest;
import test.dto.ConfirmationResponse;
import test.dto.ConfirmationResult;
import test.dto.JwtResponse;
import test.dto.OtpData;
import test.dto.OtpResponse;
import test.model.User;
import test.service.EmailService;
import test.service.OtpService;
import test.service.SecurityService;
import test.service.UserComponent;

import java.util.UUID;

import static test.model.Role.USER;

@Log4j2
@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;
    private final UserComponent userComponent;
    private final OtpService otpService;
    private final EmailService emailService;

    @Override
    public OtpResponse register(ClientRegisterRequest request) {
        checkIsEmptyClient(request.getLogin());
        var otpDate = otpService.generate(request);
        sendEmail(request.getEmail(), otpDate);
        log.info("Register new client with login: '{}'", request.getLogin());
        return new OtpResponse(otpDate.getOperationId(), otpDate.getTtlMinutes());
    }

    @Override
    public JwtResponse auth(String login, String password) {
        var user = userComponent.getByLogin(login);
        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Не верный пароль");
        }
        return new JwtResponse(jwtProvider.generateToken(login));
    }

    @Override
    public ConfirmationResponse confirm(UUID operationId, String otpCode) {
        var otpResult = otpService.check(operationId, otpCode);

        if (!otpResult.isResult()) {
            return ConfirmationResponse.builder()
                    .result(otpResult.isResult())
                    .message(otpResult.getMessage())
                    .build();
        }

        var client = createUser(otpResult);
        var token = jwtProvider.generateToken(client.getLogin());

        return ConfirmationResponse.builder()
                .result(otpResult.isResult())
                .message(otpResult.getMessage())
                .token(token)
                .build();
    }

    private User createUser(ConfirmationResult result) {
        var user = User.builder()
                .phone(result.getPhone())
                .fullName(result.getFullName())
                .email(result.getEmail())
                .password(encoder.encode(result.getPassword()))
                .login(result.getLogin())
                .role(USER).build();
        return userComponent.create(user);
    }

    private void checkIsEmptyClient(String login) {
        var userFind = userComponent.findByLogin(login);

        if (userFind.isPresent()) {
            throw new RuntimeException("Пользователь с таким логином уже существует");
        }
    }

    private void sendEmail(String email, OtpData data) {
        emailService.sendSimpleEmail(email,
                "Одноразовый пароль для подтверждения почты",
                "Ваш пароль:" + data.getOtpCode());
    }
}

