package test.redis;

import lombok.Getter;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import java.util.Random;

@Getter
@Service
public class OtpService {
    final int timeInSeconds = 60;
    Jedis jedis = new Jedis("localhost");
    private String otpKey;

    public void newOtp() {
        Random rnd = new Random();
        int randomKey = 100000 + rnd.nextInt(900000);
        otpKey = String.valueOf(randomKey);
        jedis.setex(otpKey, timeInSeconds, "4");
    }

    public String getOtp(String otpKey) {
        if (jedis.exists(otpKey)) {
            return "You are welcome!";
        }
        return "Key not found";
    }

//    public void newOtp(String session_key) {
//        Otp otp = new Otp(session_key);
//
//        if (redisClient.(otp.session_key.toString(),
//                String.valueOf(otp.otp_value)).toBoolean()) {
//
//            redisClient.setex(otp.session_key.toString(),
//                    String.valueOf(timeInSeconds),
//                    String.valueOf(otp.otp_value));
//        }
//    }

//    public String getOtpTTL(String session_key) {
//        return redisClient.ttl(session_key).toString();
//    }
//
//    public boolean keyExists(String session_key) {
//        return redisClient.exists(Arrays.asList(session_key)).toBoolean();
//    }

//
//    public int generateOTP(String key){
//        Random random = new Random();
//        int otp = 100000 + random.nextInt(900000);
//        otpCache.put(key, otp);
//        return otp;
//    }
//

}
