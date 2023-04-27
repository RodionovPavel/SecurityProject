package test.service;

import test.dto.ClientRegisterRequest;

import java.util.UUID;

public interface UserService {

    void create(ClientRegisterRequest registerDto);

    void update(UUID id, ClientRegisterRequest registerDto);


}
