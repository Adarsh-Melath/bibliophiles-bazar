package com.adarsh.backend.feature.auth.application.usecase;

import com.adarsh.backend.feature.auth.application.dto.CreateUserCommand;

public interface CreateUserUseCase {
    void execute(CreateUserCommand command);
}
