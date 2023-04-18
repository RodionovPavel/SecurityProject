package test.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import test.dto.JwtResponse;
import test.dto.ClientRegisterRequest;
import test.mapper.ProfileMapper;
import test.repository.UserRepository;
import test.model.User;
import test.config.security.JwtProvider;
import test.service.SecurityService;
import test.service.UserComponent;

import java.util.UUID;

import static test.model.Role.USER;

@Log4j2
@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private  final JwtProvider jwtProvider;
    private final ProfileMapper profileMapper;
    private final UserComponent userComponent;

    @Override
    public JwtResponse register(ClientRegisterRequest request) {
        checkIsEmptyClient(request.getLogin());
        var client  = createUser(request);
        log.info("Register new client with id: '{}'", client.getId());
        return new JwtResponse(jwtProvider.generateToken(client.getLogin()));
    }

    @Override
    public JwtResponse auth(String login, String password) {
        var user = userComponent.getByLogin(login);
        if (!encoder.matches(password, user.getPassword())) {
           throw new RuntimeException("Не верный пароль");
        }
        return new JwtResponse(jwtProvider.generateToken(login));
    }

    private User createUser(ClientRegisterRequest request) {
        var user = profileMapper.fromRegisterDto(request);
        user.setPassword(encoder.encode(request.getPassword()));
        user.setId(UUID.randomUUID());
        user.setRole(USER);
        return userComponent.create(user);
    }

    private void checkIsEmptyClient(String login) {
        var userFind = userComponent.findByLogin(login);

        if (userFind.isPresent()) {
            throw new RuntimeException("Пользователь с таким логином уже существует");
        }
    }
}

