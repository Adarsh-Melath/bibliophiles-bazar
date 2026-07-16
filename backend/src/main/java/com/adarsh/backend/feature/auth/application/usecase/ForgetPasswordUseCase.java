package com.adarsh.backend.feature.auth.application.usecase;

import com.adarsh.backend.feature.auth.application.dto.command.ForgetPasswordCommand;

public interface ForgetPasswordUseCase {
    void execute(ForgetPasswordCommand command);
}
