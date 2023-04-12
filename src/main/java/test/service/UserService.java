package test.service;


import test.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    void create(User user);

    void update(UUID id, User user);

    User getUserById(int id);

    void deleteById(int id);

    List<User> readAll();

    long size();
}
