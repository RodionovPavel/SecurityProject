package test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.dto.JwtResponse;
import test.dto.LoginDto;
import test.dto.RegisterDto;
import test.mapper.ProfileMapper;
import test.model.User;
import test.service.ProfileService;
import test.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    private final ProfileMapper profileMapper;

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok(profileService.register(registerDto));
    }

    @PostMapping("/auth")
    public ResponseEntity<JwtResponse> auth(@RequestBody LoginDto dto) {
        return ResponseEntity.ok(profileService.auth(dto.getLogin(), dto.getPassword()));
    }
}
