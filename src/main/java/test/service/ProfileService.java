package test.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import test.dto.JwtResponse;
import test.dto.RegisterDto;

public interface ProfileService extends UserDetailsService {

    JwtResponse register(RegisterDto registerDto);

    JwtResponse auth(String login, String password);

}
