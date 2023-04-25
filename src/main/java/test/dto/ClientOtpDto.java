package test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@AllArgsConstructor
public class ClientOtpDto {

        @NotBlank
        private String otpKey;

        @NotBlank
        private Integer countAttempts;

        @NotBlank
        private Date createData;

}
