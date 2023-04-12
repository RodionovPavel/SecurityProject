package test.controller;

import test.dao.UserDAO;
import test.model.User;
import test.service.ProfileService;
import test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void updateUser(@PathVariable int id, @RequestBody User user) {
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
