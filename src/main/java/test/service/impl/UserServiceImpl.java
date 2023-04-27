package test.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import test.dto.ClientRegisterRequest;
import test.mapper.ProfileMapper;
import test.model.User;
import test.service.UserComponent;
import test.service.UserService;

import java.util.Optional;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder encoder;

    private final ProfileMapper profileMapper;

    private final UserComponent userComponent;

    @Override
    public void create(ClientRegisterRequest registerDto) {
        User user = profileMapper.fromRegisterDto(registerDto);
        userComponent.create(user);
    }

    @Override
    public void update(UUID id, ClientRegisterRequest registerDto) {
        log.info("Update user id '{}', userName '{}'", id, registerDto.getFullName());
        Optional<User> findUser = userComponent.findById(id);

        if (findUser.isPresent()) {
            User user = profileMapper.fromRegisterDto(registerDto);
            user.setPassword(encoder.encode(user.getPassword()));
            userComponent.create(user);
        }
    }
}
