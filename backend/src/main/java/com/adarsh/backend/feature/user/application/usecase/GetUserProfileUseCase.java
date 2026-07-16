package com.adarsh.backend.feature.user.application.usecase;

import com.adarsh.backend.feature.user.application.dto.result.GetUserProfileResult;

public interface GetUserProfileUseCase {
    GetUserProfileResult execute(String email);
}
