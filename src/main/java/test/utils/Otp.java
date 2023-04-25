package test.utils;

import java.util.Random;

public class Otp {

    public static String createOtp() {
        Random rnd = new Random();
        int randomKey = 100000 + rnd.nextInt(900000);
        return String.valueOf(randomKey);
    }
}
