package test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test.model.User;
import test.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String index() {
        return "Hello, world!!!";
    }

    @PostMapping("/user")
    public void create(@RequestBody User user) {
        userService.create(user);
    }

    @PutMapping(value = "/{id}")
    public void updateUser(@PathVariable UUID id, @RequestBody User user) {
        userService.update(id, user);
    }

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable int id) {
        userService.deleteById(id);
    }

    @GetMapping("/list")
    public List<User> readAll() {
        return userService.readAll();
    }

    @GetMapping("/admin/size")
    public long size() {
        return userService.size();
    }

}
