package com.adarsh.backend.shared.application.usecase;

import com.adarsh.backend.shared.application.dto.VerifyOtpCommand;

public interface VerifyOtpUseCase {
    void execute(VerifyOtpCommand command);
}
