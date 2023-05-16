package test.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import test.dto.ClientRegisterRequest;
import test.mapper.ProfileMapper;
import test.model.User;
import test.service.ResultComponent;
import test.service.UserComponent;
import test.service.UserService;

import javax.transaction.Transactional;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder encoder;

    private final ProfileMapper profileMapper;

    private final UserComponent userComponent;

    private final ResultComponent resultComponent;

    @Override
    @Transactional
    public void create(ClientRegisterRequest registerDto) {
        User user = profileMapper.fromRegisterDto(registerDto);
        userComponent.create(user);
        resultComponent.create(user);
    }

    @Override
    public void update(UUID id, ClientRegisterRequest registerDto) {
        User findUser = userComponent.findById(id);

        User user = profileMapper.fromRegisterDto(registerDto);
        findUser.setRole(user.getRole());
        findUser.setEmail(user.getEmail());
        findUser.setPhone(user.getPhone());
        findUser.setFullName(user.getFullName());
        findUser.setChatId(user.getChatId());
        findUser.setPassword(encoder.encode(user.getPassword()));
        userComponent.create(user);

        log.info("Update user id '{}', userName '{}'", id, registerDto.getFullName());
    }
}
