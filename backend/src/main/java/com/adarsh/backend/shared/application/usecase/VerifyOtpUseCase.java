package com.adarsh.backend.shared.application.usecase;

import com.adarsh.backend.shared.application.dto.VerifyOtpCommand;
import com.adarsh.backend.shared.application.dto.VerifyOtpResult;

public interface VerifyOtpUseCase {
    VerifyOtpResult execute(VerifyOtpCommand command);
}
