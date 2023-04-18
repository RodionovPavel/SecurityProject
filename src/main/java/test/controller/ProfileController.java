package test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.dto.JwtResponse;
import test.dto.LoginDto;
import test.dto.RegisterDto;
import test.service.ProfileService;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    @Tag(name = "Регистрация пользователя", description = "Регистрация нового пользователя")
    @Operation(
            summary = "Регистрация пользователя",
            description = "Позволяет зарегистрировать пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Пользователь зарегистрирован"),
            @ApiResponse(responseCode = "400",
                    description = "Ошибка") })
    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@Valid @RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok(profileService.register(registerDto));
    }

    @PostMapping("/auth")
    public ResponseEntity<JwtResponse> auth(@RequestBody LoginDto dto) {
        return ResponseEntity.ok(profileService.auth(dto.getLogin(), dto.getPassword()));
    }
}
