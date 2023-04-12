package test.service;

import test.model.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import test.dao.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(int id, User user) {
        Optional<User> findUser = userRepository.findById(id);

        if (findUser.isPresent()) {
            User updateUser = new User();
            updateUser.setId(id);
            updateUser.setLogin(user.getLogin());
            updateUser.setPhone(user.getPhone());
            updateUser.setEmail(user.getEmail());
//            updateUser.setUserRole(user.getUserRole());
        userRepository.save(updateUser);
        }
    }

    @Override
    public User getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> readAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public long size() {
        return userRepository.count();
    }
}
