package com.adarsh.backend.feature.user.application.usecase;

import com.adarsh.backend.feature.user.application.dto.command.UpdateUserProfileCommand;
import com.adarsh.backend.feature.user.application.dto.result.UpdateUserProfileResult;

public interface UpdateUserProfileUseCase {
    UpdateUserProfileResult execute(String email, UpdateUserProfileCommand command);
}
