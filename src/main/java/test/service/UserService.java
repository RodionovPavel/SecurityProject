package test.service;

import test.dto.RegisterDto;

import java.util.UUID;

public interface UserService {

    void create(RegisterDto registerDto);

    void update(UUID id, RegisterDto registerDto);


}
