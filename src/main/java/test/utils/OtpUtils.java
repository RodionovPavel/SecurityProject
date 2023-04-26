package test.utils;

import java.util.Random;

public class OtpUtils {

    public static String generateOtpCode() {
        Random rnd = new Random();
        int randomKey = 100000 + rnd.nextInt(900000);
        return String.valueOf(randomKey);
    }
}
