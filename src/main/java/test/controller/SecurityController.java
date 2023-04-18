package test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.dto.ClientRegisterRequest;
import test.dto.JwtResponse;
import test.dto.ClientAuthRequest;
import test.service.SecurityService;

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
            @ApiResponse(responseCode = "400", description = "Ошибка") })
    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@Valid @RequestBody ClientRegisterRequest request) {
        log.info("Register new client with email: '{}'", request.getEmail());
        return ResponseEntity.ok(profileService.register(request));
    }

    @PostMapping("/auth")
    public ResponseEntity<JwtResponse> auth(@RequestBody ClientAuthRequest request) {
        log.info("Auth with login: '{}'", request.getLogin());
        return ResponseEntity.ok(profileService.auth(request.getLogin(), request.getPassword()));
    }
}
