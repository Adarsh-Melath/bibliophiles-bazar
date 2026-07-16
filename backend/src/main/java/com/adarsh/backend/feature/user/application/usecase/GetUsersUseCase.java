package com.adarsh.backend.feature.user.application.usecase;

import com.adarsh.backend.feature.user.application.dto.result.GetUsersResult;
import com.adarsh.backend.feature.user.domain.model.Role;
import com.adarsh.backend.shared.domain.pagination.PageResult;

public interface GetUsersUseCase {
    PageResult<GetUsersResult> execute(String keyword, Role role, Boolean enabled, int page, int size);
}
