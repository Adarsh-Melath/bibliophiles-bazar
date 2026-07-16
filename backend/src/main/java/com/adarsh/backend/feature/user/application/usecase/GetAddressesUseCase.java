package com.adarsh.backend.feature.user.application.usecase;

import com.adarsh.backend.feature.user.application.dto.result.GetAddressResult;

import java.util.List;


public interface GetAddressesUseCase {
    List<GetAddressResult> execute(String email);
}
