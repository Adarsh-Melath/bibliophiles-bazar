package com.adarsh.backend.feature.auth.application.usecase;

import com.adarsh.backend.feature.auth.application.dto.command.VerifyResetOtpCommand;
import com.adarsh.backend.feature.auth.application.dto.result.VerifyResetOtpResult;

public interface VerifyResetOtpUseCase {
    VerifyResetOtpResult execute(VerifyResetOtpCommand command);
}
