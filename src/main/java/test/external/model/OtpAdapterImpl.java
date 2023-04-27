package test.external.model;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.dto.ClientOtpRequest;
import test.external.OtpAdapter;

@Service
@RequiredArgsConstructor
public class OtpAdapterImpl implements OtpAdapter {
//    private final RedisTemplate<String, Object> redisTemplate;
//
//
//    private static ClientOtpRequest clientOtpRequest;


    @Override
    public void newOtp() {
//        UUID uuidOperationId = UUID.randomUUID();
//        String fullOperationId = CACHE_KEY + uuidOperationId.toString();
//        String otpKey = OtpUtils.generateOtpCode();
//        Date creationData = new Date();
//        ClientOtp clientOtpDto = new ClientOtp(otpKey, DEFAULT_COUNT_ATTEMPTS, creationData);
//        redisTemplate.opsForValue().set(fullOperationId, clientOtpDto);
//        redisTemplate.expire(fullOperationId, Duration.ofSeconds(DEFAULT_TTL_SECONDS));
    }

    @Override
    public String checkOtp(ClientOtpRequest clientOtpRequest) {
////        System.out.println(clientOtpRequest);
//        String fullOperationId = clientOtpRequest.getFullOperationId();
////        ClientOtpRequest newC = redisTemplate.opsForHash().values("test");
//        System.out.println(redisTemplate.opsForValue().get(fullOperationId));
//        return "test";
        return null;
    }
}

