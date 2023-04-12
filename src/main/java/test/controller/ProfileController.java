package test.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import test.dto.LoginDto;
import test.dto.RegisterDto;
//import test.mapper.ProfileMapper;
import test.model.User;
import test.service.ProfileService;
import test.service.UserService;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

//    @Autowired
//    private ProfileMapper profileMapper;

    @PostMapping("/register")
    public void register(@RequestBody RegisterDto registerDto) {
//        User user = profileMapper.fromRegisterDto(registerDto);
        User user = new User();
        user.setPassword(registerDto.getPassword());
        user.setLogin(registerDto.getLogin());
        profileService.register(user);
    }

    @PostMapping("/auth")
    public String auth(@RequestBody LoginDto dto) {
        return profileService.auth(dto.getLogin(), dto.getPassword());
    }

    @GetMapping("/me")
    public User me(@AuthenticationPrincipal User user) {
        return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
