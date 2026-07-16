package com.adarsh.backend.feature.auth.application.usecase;

import com.adarsh.backend.feature.auth.application.dto.command.LoginCommand;
import com.adarsh.backend.feature.auth.application.dto.result.LoginResult;

public interface LoginUseCase {
    LoginResult execute(LoginCommand command);
}
