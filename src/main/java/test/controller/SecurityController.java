package test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.dto.ClientAuthRequest;
import test.dto.ClientRegisterRequest;
import test.dto.ConfirmationRequest;
import test.dto.ConfirmationResponse;
import test.dto.JwtResponse;
import test.dto.OtpResponse;
import test.service.SecurityService;

import javax.validation.Valid;

@Log4j2
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/security")
public class SecurityController {

    private final SecurityService profileService;

    @Tag(name = "Регистрация пользователя", description = "Регистрация нового пользователя")
    @Operation(
            summary = "Регистрация пользователя",
            description = "Позволяет зарегистрировать пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь зарегистрирован"),
            @ApiResponse(responseCode = "400", description = "Ошибка")})
    @PostMapping("/register")
    public ResponseEntity<OtpResponse> register(@Valid @RequestBody ClientRegisterRequest request) {
        log.info("Register new client with email: '{}'", request.getEmail());
        return ResponseEntity.ok(profileService.register(request));
    }

    @PostMapping("/auth")
    public ResponseEntity<JwtResponse> auth(@RequestBody ClientAuthRequest request) {
        log.info("Auth with login: '{}'", request.getLogin());
        return ResponseEntity.ok(profileService.auth(request.getLogin(), request.getPassword()));
    }

    @PostMapping("/confirm")
    public ResponseEntity<ConfirmationResponse> confirm(@RequestBody ConfirmationRequest request) {
        log.info("Confirmation with operationId: '{}'", request.getOperationId());
        return ResponseEntity.ok(profileService.confirm(request.getOperationId(), request.getOtpCode()));
    }
}
