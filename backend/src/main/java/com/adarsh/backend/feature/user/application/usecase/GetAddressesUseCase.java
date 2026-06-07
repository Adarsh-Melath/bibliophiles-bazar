package com.adarsh.backend.feature.user.application.usecase;

import java.util.List;

import com.adarsh.backend.shared.application.dto.GetAddressResult;


public interface GetAddressesUseCase {
    List<GetAddressResult> execute(String email);
}
