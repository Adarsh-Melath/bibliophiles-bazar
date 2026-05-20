package com.adarsh.backend.feature.user.application.usecase;

import com.adarsh.backend.feature.user.application.dto.CreateUserCommand;

public interface CreateUserUseCase {
    void execute(CreateUserCommand command);
}
