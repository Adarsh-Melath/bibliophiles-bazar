package com.adarsh.backend.feature.auth.application.usecase;

import com.adarsh.backend.feature.auth.application.dto.VerifyResetOtpCommand;

public interface VerifyResetOtpUseCase {
    String execute(VerifyResetOtpCommand command);
}
