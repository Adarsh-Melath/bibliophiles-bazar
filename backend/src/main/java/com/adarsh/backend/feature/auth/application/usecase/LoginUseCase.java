package com.adarsh.backend.feature.auth.application.usecase;

import com.adarsh.backend.feature.auth.application.dto.LoginCommand;

public interface LoginUseCase {
    void execute(LoginCommand command);
}
