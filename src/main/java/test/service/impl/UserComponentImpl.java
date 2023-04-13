package test.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import test.model.User;
import test.repository.UserRepository;
import test.service.UserComponent;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserComponentImpl implements UserComponent {

    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        log.info("User '{}' is created", user.getLogin());
        user.setId(UUID.randomUUID());
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUserById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
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
