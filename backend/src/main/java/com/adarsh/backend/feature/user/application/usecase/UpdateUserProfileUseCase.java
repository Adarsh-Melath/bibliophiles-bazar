package com.adarsh.backend.feature.user.application.usecase;

import com.adarsh.backend.feature.user.application.dto.UpdateUserProfileCommand;
import com.adarsh.backend.feature.user.application.dto.UpdateUserProfileResult;

public interface UpdateUserProfileUseCase {
    UpdateUserProfileResult execute(UpdateUserProfileCommand command);
}
