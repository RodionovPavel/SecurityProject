package test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import test.dto.ClientOtpRequest;
import test.external.model.OtpAdapterImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/otp")
public class OtpController {

//    private final ClientOtpRequest clientOtpRequest;
    private final OtpAdapterImpl otpAdapterImpl;

    @PostMapping()
    public String postTestOtp() {
        otpAdapterImpl.newOtp();
        return "success";
    }

    @GetMapping()
    public String getTestOtp(@RequestBody ClientOtpRequest clientOtpRequest) {
        otpAdapterImpl.checkOtp(clientOtpRequest);
        return "success";
    }

}
