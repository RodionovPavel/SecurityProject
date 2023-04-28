package test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class ClientOtp {

        private String otpCode;

        private Integer countAttempts;

        private Date createData;

        private String login;

        private String password;

        private String fullName;

        private String email;

        private String phone;

}
