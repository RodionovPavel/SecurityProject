package test.service;

import test.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserComponent {

    User create(User user);

    User getUserById(UUID id);

    Optional<User> findByLogin(String login);

    User getByLogin(String login);

    User findById(UUID id);

    void deleteById(UUID id);

    List<User> readAll();

    long size();

    User findByChatId(Long chatId);

    Optional<User> getByChatId(Long chatId);

}
