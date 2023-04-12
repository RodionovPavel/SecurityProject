package test.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import test.model.User;

public interface ProfileService extends UserDetailsService {

    void register(User user);

    String auth(String login, String password);

}
