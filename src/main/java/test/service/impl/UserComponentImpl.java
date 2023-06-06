package test.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import test.dto.ClientResponse;
import test.exsap.CustomException;
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
        log.info("User '{}' is created", user.getLogin());
        userRepository.save(user);
        return user;
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomException("Пользователь с id" + id + " не найден"));
    }

    @Override
    public ClientResponse getClient() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        return ClientResponse.builder()
                .email(user.getEmail())
                .phone(user.getPhone())
                .fullName(user.getFullName())
                .login(user.getLogin()).build();
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new CustomException("Пользователь с логином " + login + " не найден"));
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
