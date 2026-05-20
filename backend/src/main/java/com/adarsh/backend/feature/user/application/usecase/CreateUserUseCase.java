package com.adarsh.backend.feature.user.application.usecase;

import com.adarsh.backend.feature.user.application.dto.CreateUserCommand;
import com.adarsh.backend.feature.user.domain.model.User;

public interface CreateUserUseCase {
    User execute(CreateUserCommand command);
}
