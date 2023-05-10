package test.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test.dto.ClientRegisterRequest;
import test.dto.ClientResponse;
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

    @GetMapping("/hello")
    public String index() {
        return "Hello, world!!!";
    }

    @PostMapping("/user")
    public void create(@RequestBody ClientRegisterRequest registerDto) {
        userService.create(registerDto);
    }

    @PutMapping(value = "/{id}")
    public void updateUser(
            @Parameter(description = "Идентификатор клиента", required = true)
            @PathVariable UUID id,
            @Parameter(description = "Новые данные пользователя", required = true)
            @RequestBody ClientRegisterRequest registerDto) {
        userService.update(id, registerDto);
    }

    @SecurityRequirement(name = "JWT")
    @GetMapping(value = "/api/v1/client")
    public ResponseEntity<ClientResponse> getClient() {
        return ResponseEntity.ok(userComponent.getClient());
    }

    @SecurityRequirement(name = "JWT")
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
