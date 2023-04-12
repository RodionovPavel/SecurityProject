package test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.dto.JwtResponse;
import test.dto.LoginDto;
import test.dto.RegisterDto;
import test.model.User;
import test.service.ProfileService;
import test.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    private final UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterDto registerDto) {
        User user = new User();
        user.setPassword(registerDto.getPassword());
        user.setLogin(registerDto.getLogin());
        user.setRole(registerDto.getRole());
        user.setFio(registerDto.getFio());
        user.setEmail(registerDto.getEmail());
        user.setPhone(registerDto.getPhone());
        profileService.register(user);
    }

    @PostMapping("/auth")
    public ResponseEntity<JwtResponse> auth(@RequestBody LoginDto dto) {
        return ResponseEntity.ok(new JwtResponse(profileService.auth(dto.getLogin(), dto.getPassword())));
    }
}
