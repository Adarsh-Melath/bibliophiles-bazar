package com.adarsh.backend.feature.auth.application.usecase;

import com.adarsh.backend.feature.auth.application.dto.ForgetPasswordCommand;

public interface ForgetPasswordUseCase {
    void execute(ForgetPasswordCommand command);
}
