package test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ClientOtp {

        private UUID clientId;

        private String otpCode;

        private Integer countAttempts;

        private Date createData;

}
