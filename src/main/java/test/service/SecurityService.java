package test.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import test.dto.ClientRegisterRequest;
import test.dto.JwtResponse;

public interface SecurityService {

    JwtResponse register(ClientRegisterRequest registerDto);

    JwtResponse auth(String login, String password);

}
