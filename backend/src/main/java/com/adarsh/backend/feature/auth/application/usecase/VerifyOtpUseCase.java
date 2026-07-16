package com.adarsh.backend.feature.auth.application.usecase;

import com.adarsh.backend.feature.auth.application.dto.command.VerifyOtpCommand;
import com.adarsh.backend.feature.auth.application.dto.result.VerifyOtpResult;

public interface VerifyOtpUseCase {
    VerifyOtpResult execute(VerifyOtpCommand command);
}
