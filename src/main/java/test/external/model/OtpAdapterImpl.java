package test.external.model;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import test.dto.ClientOtpDto;
import test.dto.ClientOtpRequest;
import test.external.OtpAdapter;
import test.utils.Otp;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;
//import redis.clients.jedis.Jedis;


//@Getter
//@Service
//public class OtpService {
//    final int timeInSeconds = 60;
//    Jedis jedis = new Jedis("localhost");
//    private String otpKey;
//
//    public void newOtp() {
//        Random rnd = new Random();
//        int randomKey = 100000 + rnd.nextInt(900000);
//        otpKey = String.valueOf(randomKey);
//        jedis.setex(otpKey, timeInSeconds, "4");
//    }
//
//    public String getOtp(String otpKey) {
//        if (jedis.exists(otpKey)) {
//            return "You are welcome!";
//        }
//        return "Key not found";
//    }
//}

@Service
@RequiredArgsConstructor
public class OtpAdapterImpl implements OtpAdapter {
    private final RedisTemplate<String, Object> redisTemplate;


    private static ClientOtpRequest clientOtpRequest;

    public static final String CACHE_KEY = "clientUser:operationId:";
    public static final Integer DEFAULT_COUNT_ATTEMPTS = 4;
    public static final Integer DEFAULT_TTL_SECONDS = 120;

    @Override
    public void newOtp() {
        UUID uuidOperationId = UUID.randomUUID();
        String fullOperationId = CACHE_KEY + uuidOperationId.toString();
        String otpKey = Otp.createOtp();
        Date creationData = new Date();
        ClientOtpDto clientOtpDto = new ClientOtpDto(otpKey, DEFAULT_COUNT_ATTEMPTS, creationData);
        redisTemplate.opsForValue().set(fullOperationId, clientOtpDto);
        redisTemplate.expire(fullOperationId, Duration.ofSeconds(DEFAULT_TTL_SECONDS));
    }

    @Override
    public String checkOtp(ClientOtpRequest clientOtpRequest) {
//        System.out.println(clientOtpRequest);
        String fullOperationId = clientOtpRequest.getFullOperationId();
//        ClientOtpRequest newC = redisTemplate.opsForHash().values("test");
        System.out.println(redisTemplate.opsForValue().get(fullOperationId));
        return "test";
    }
}

