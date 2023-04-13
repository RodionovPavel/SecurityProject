package test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test.dto.RegisterDto;
import test.model.User;
import test.service.UserComponent;
import test.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserComponent userComponent;


//    @Autowired
//    public UserController(UserService userService, UserComponent userComponent) {
//        this.userService = userService;
//        this.userComponent = userComponent;
//    }

    @GetMapping("/hello")
    public String index() {
        return "Hello, world!!!";
    }

    @PostMapping("/user")
    public void create(@RequestBody RegisterDto registerDto) {
        userService.create(registerDto);
    }

    @PutMapping(value = "/{id}")
    public void updateUser(@PathVariable UUID id, @RequestBody RegisterDto registerDto) {
        userService.update(id, registerDto);
    }

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable UUID id) {
        return userComponent.getUserById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable UUID id) {
        userComponent.deleteById(id);
    }

    @GetMapping("/list")
    public List<User> readAll() {
        return userComponent.readAll();
    }

    @GetMapping("/admin/size")
    public long size() {
        return userComponent.size();
    }

}
