package com.adarsh.backend.feature.auth.application.usecase;

import com.adarsh.backend.feature.auth.application.dto.command.RegisterUserCommand;

public interface RegisterUserUseCase {
    void execute(RegisterUserCommand command);
}
