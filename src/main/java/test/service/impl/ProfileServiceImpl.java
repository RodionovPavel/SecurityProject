package test.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import test.dto.JwtResponse;
import test.dto.RegisterDto;
import test.mapper.ProfileMapper;
import test.repository.UserRepository;
import test.model.User;
import test.config.security.JwtProvider;
import test.service.ProfileService;
import test.service.UserComponent;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private  final JwtProvider jwtProvider;
    private final ProfileMapper profileMapper;
    private final UserComponent userComponent;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username).orElseThrow();
    }

    @Override
    public JwtResponse register(RegisterDto registerDto) { //Dto вернуть токен
        User user = profileMapper.fromRegisterDto(registerDto);
        user.setPassword(encoder.encode(registerDto.getPassword()));
        userComponent.create(user);
        JwtResponse jwtResponse = new JwtResponse(jwtProvider.generateToken(user.getLogin()));
        return jwtResponse;
    }

    @Override
    public JwtResponse auth(String login, String password) { //Возвращ dto
        User user = userRepository.findByLogin(login).orElseThrow();
        if (encoder.matches(password, user.getPassword())) {
            JwtResponse jwtResponse = new JwtResponse(jwtProvider.generateToken(login));
            return jwtResponse;
        }

        return null;
    }
}

