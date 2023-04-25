package test.external;

import test.dto.ClientOtpRequest;

public interface OtpAdapter {
    void newOtp();

    String checkOtp(ClientOtpRequest clientOtpRequest);

}
