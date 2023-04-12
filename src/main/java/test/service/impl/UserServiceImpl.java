package test.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import test.repository.UserRepository;
import test.model.User;
import test.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public void create(User user) { //Переписать всё на дто
        user.setId(UUID.randomUUID());
        userRepository.save(user);
    }

    @Override
    public void update(UUID id, User user) {
        log.info("Update user id '{}', userName '{}'", id, user.getFio());
        Optional<User> findUser = userRepository.findById(id);

        if (findUser.isPresent()) {
            User updateUser = new User();
            updateUser.setId(id);
            updateUser.setLogin(user.getLogin());
            updateUser.setFio((user.getFio()));
            updateUser.setPassword(encoder.encode(user.getPassword()));
            updateUser.setPhone(user.getPhone());
            updateUser.setEmail(user.getEmail());
            updateUser.setRole(user.getRole());
            userRepository.save(updateUser);
        }
    }

    @Override
    public User getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> readAll() {
        return userRepository.findAll();
    }

    @Override
    public long size() {
        return userRepository.count();
    }
}
