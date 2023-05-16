package test.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import test.config.security.JwtProvider;
import test.dto.ClientRegisterRequest;
import test.dto.ConfirmationResponse;
import test.dto.JwtResponse;
import test.dto.OtpData;
import test.dto.OtpResponse;
import test.mapper.ProfileMapper;
import test.model.User;
import test.service.EmailService;
import test.service.OtpService;
import test.service.SecurityService;
import test.service.UserComponent;

import java.util.UUID;

import static test.model.enums.Role.USER;

@Log4j2
@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;
    private final ProfileMapper profileMapper;
    private final UserComponent userComponent;
    private final OtpService otpService;
    private final EmailService emailService;

    @Override
    public OtpResponse register(ClientRegisterRequest request) {
        checkIsEmptyClient(request.getLogin());
        var client = createUser(request);
        var otpDate = otpService.generate(client.getId());
        sendEmail(client.getEmail(), otpDate);
        log.info("Register new client with id: '{}'", client.getId());
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

        var client = userComponent.getUserById(otpResult.getClientId());
        var token = jwtProvider.generateToken(client.getLogin());

        return ConfirmationResponse.builder()
                .result(otpResult.isResult())
                .message(otpResult.getMessage())
                .token(token)
                .build();
    }

    private User createUser(ClientRegisterRequest request) {
        var user = profileMapper.fromRegisterDto(request);
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(USER);
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

