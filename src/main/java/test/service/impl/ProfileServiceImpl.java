package test.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import test.repository.UserRepository;
import test.model.User;
import test.config.security.JwtProvider;
import test.service.ProfileService;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private  final JwtProvider jwtProvider;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username).orElseThrow();
    }

    @Override
    public void register(User user) { //Dto
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public String auth(String login, String password) { //Возвращ dto
        User user = userRepository.findByLogin(login).orElseThrow();
        if (encoder.matches(password, user.getPassword())) {
            return jwtProvider.generateToken(login);
        }

        return "not auth";
    }
}

