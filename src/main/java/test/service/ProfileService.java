package test.service;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import test.dao.UserRepository;
import test.model.Role;
import test.model.User;
import test.security.JwtProvider;

@Component
public class ProfileService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }

    public void register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public String auth(String login, String password) {
        User user = userRepository.findByLogin(login);
        System.out.println("password: " + password);
        System.out.println("getPassword: " + user.getPassword());
        System.out.println(encoder.matches(password, user.getPassword()));
        if (encoder.matches(password, user.getPassword())) {
            System.out.println("find");
            return jwtProvider.generateToken(login);
        }

        return "not auth";
    }
}

