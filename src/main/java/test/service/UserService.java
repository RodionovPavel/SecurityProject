package test.service;


import test.model.User;

import java.util.List;

public interface UserService {
    void create(User user);
    void update(int id, User user);
    User getUserById(int id);
    void deleteById(int id);
    List<User> readAll();
    long size();
}
