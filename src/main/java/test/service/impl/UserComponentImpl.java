package test.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import test.model.User;
import test.repository.UserRepository;
import test.service.UserComponent;

import javax.persistence.EntityNotFoundException;
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
        userRepository.save(user);
        log.info("User '{}' is created", user.getLogin());
        return user;
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с id" + id + " не найден"));
    }

    @Override
    public User findByChatId(Long chatId) {
        return userRepository.findByChatId(chatId)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с chatId " + chatId + " не найден"));
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с логином " + login + " не найден"));
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с логином " + id + " не найден"));
    }

    @Override
    public List<User> readAll() {
        return userRepository.findAll();
    }

    @Override
    public long size() {
        return userRepository.count();
    }

    @Override
    public Optional<User> getByChatId(Long chatId) {
        return userRepository.findByChatId(chatId);
    }
}
