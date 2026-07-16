package com.adarsh.backend.feature.auth.application.usecase;

import com.adarsh.backend.feature.auth.application.dto.result.RefreshAccessTokenResult;

public interface RefreshAccessTokenUseCase {
    RefreshAccessTokenResult execute(String refreshToken);
}
