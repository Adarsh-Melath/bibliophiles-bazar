package com.adarsh.backend.feature.auth.application.usecase;

import com.adarsh.backend.feature.auth.application.dto.VerifyResetOtpCommand;
import com.adarsh.backend.feature.auth.application.dto.VerifyResetOtpResult;

public interface VerifyResetOtpUseCase {
    VerifyResetOtpResult execute(VerifyResetOtpCommand command);
}
