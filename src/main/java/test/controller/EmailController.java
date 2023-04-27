package test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;
import test.dto.ClientOtpRequest;
import test.external.model.OtpAdapterImpl;
import test.service.EmailService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {


    private final OtpAdapterImpl otpService;

    private final EmailService emailService;

//    private final ClientOtpRequest clientOtpRequest;

//    @GetMapping(value = "/simple-email/{user-email}")
//    public @ResponseBody ResponseEntity sendSimpleEmail(@PathVariable("user-email") String email) {
//        emailService.sendSimpleEmail(email, "Your security code", otpService.getOtp());
//    }
}
