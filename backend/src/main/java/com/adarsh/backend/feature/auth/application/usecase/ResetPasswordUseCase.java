package com.adarsh.backend.feature.auth.application.usecase;

import com.adarsh.backend.feature.auth.application.dto.ResetPasswordCommand;

public interface ResetPasswordUseCase {
    void execute(ResetPasswordCommand command);
}