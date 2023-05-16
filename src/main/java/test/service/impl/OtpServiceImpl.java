package test.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.config.CustomRedisTemplate;
import test.dto.ClientOtp;
import test.dto.ConfirmationResult;
import test.dto.OtpData;
import test.service.OtpService;
import test.utils.OtpUtils;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    public static final String CACHE_KEY = "otp:confirmation:";
    public static final Integer DEFAULT_TTL_SECONDS = 420;
    public static final Integer DEFAULT_COUNT_ATTEMPTS = 4;


    private final CustomRedisTemplate<ClientOtp> redisTemplate;

    @Override
    public OtpData generate(UUID clientId) {
        var operationId = UUID.randomUUID();
        var otpCode = OtpUtils.generateOtpCode();
        saveRedisOtp(operationId, clientId, otpCode);
        return new OtpData(operationId, clientId, Duration.ofSeconds(DEFAULT_TTL_SECONDS).toMinutes(), otpCode);
    }

    @Override
    public ConfirmationResult check(UUID operationId, String otp) {
        var clientOtp = getOtp(operationId);

        if (clientOtp.getCountAttempts() == 0) {
            return ConfirmationResult.builder()
                    .result(false)
                    .message("Превышен лимит попыток").build();
        }

        if (!otp.equals(clientOtp.getOtpCode())) {
            var attempts = clientOtp.getCountAttempts();
            clientOtp.setCountAttempts(attempts - 1);
            updateRedisOtp(clientOtp, operationId);
            return ConfirmationResult.builder()
                    .result(false)
                    .message("Неверный код").build();
        }

        return ConfirmationResult.builder()
                .clientId(clientOtp.getClientId())
                .result(true).build();
    }

    private void saveRedisOtp(UUID operationId, UUID clientId, String otpCode) {
        var clientOtp = new ClientOtp(clientId, otpCode, DEFAULT_COUNT_ATTEMPTS, new Date());
        var kay = CACHE_KEY + operationId;
        redisTemplate.opsForValue().set(kay, clientOtp);
        redisTemplate.expire(kay, Duration.ofSeconds(DEFAULT_TTL_SECONDS));
    }

    private void updateRedisOtp(ClientOtp clientOtp, UUID operationId) {
        var kay = CACHE_KEY + operationId;
        redisTemplate.opsForValue().set(kay, clientOtp);
    }

    private ClientOtp getOtp(UUID operationId) {
        var kay = CACHE_KEY + operationId;
        return redisTemplate.opsForValue().get(kay);
    }

}
