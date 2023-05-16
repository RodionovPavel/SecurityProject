package test.external.model;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.dto.ClientOtpRequest;
import test.external.OtpAdapter;

@Service
@RequiredArgsConstructor
public class OtpAdapterImpl implements OtpAdapter {

    @Override
    public void newOtp() {
    }

    @Override
    public String checkOtp(ClientOtpRequest clientOtpRequest) {
        return null;
    }

}

