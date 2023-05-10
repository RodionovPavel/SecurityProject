package test.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientResponse {

    private String login;

    private String fullName;

    private String email;

    private String phone;

}
