package test.service;

import test.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserComponent {

    User create(User user);

    User getUserById(UUID id);

    Optional<User> findById(UUID id);

    void deleteById(UUID id);

    List<User> readAll();

    long size();
}
