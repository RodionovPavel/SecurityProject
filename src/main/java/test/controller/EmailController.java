package test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import test.external.model.OtpAdapterImpl;
import test.service.EmailService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final OtpAdapterImpl otpService;

    private final EmailService emailService;

}
