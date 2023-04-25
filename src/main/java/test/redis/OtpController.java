package test.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
public class OtpController {

    @Autowired
    OtpService otpService;

    @PostMapping()
    public String postTestOtp() {
        otpService.newOtp();
        return otpService.getOtpKey();
    }

    @GetMapping(value = "/{otpKey}")
    public String getTestOtp(@PathVariable String otpKey) {
        return otpService.getOtp(otpKey);
    }

}
