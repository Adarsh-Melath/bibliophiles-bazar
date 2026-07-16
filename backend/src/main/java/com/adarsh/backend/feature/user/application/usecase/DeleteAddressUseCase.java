package com.adarsh.backend.feature.user.application.usecase;

public interface DeleteAddressUseCase {
    void execute(String email, Long addressId);
}
