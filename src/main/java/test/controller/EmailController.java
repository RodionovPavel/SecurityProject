package test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;
import test.redis.OtpService;
import test.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    OtpService otpService;
    @Autowired
    EmailService emailService;

    @GetMapping(value = "/simple-email/{user-email}")
    public @ResponseBody ResponseEntity sendSimpleEmail(@PathVariable("user-email") String email) {
        try {
            emailService.sendSimpleEmail(email, "Your security code", otpService.getOtpKey());
        } catch (MailException mailException) {
            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
    }
}
