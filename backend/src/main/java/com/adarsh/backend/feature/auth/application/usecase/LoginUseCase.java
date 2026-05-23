package com.adarsh.backend.feature.auth.application.usecase;

import com.adarsh.backend.feature.auth.application.dto.LoginCommand;
import com.adarsh.backend.feature.auth.application.dto.LoginResult;

public interface LoginUseCase {
    LoginResult execute(LoginCommand command);
}
