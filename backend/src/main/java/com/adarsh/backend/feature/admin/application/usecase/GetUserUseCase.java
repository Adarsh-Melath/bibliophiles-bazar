package com.adarsh.backend.feature.admin.application.usecase;

import com.adarsh.backend.feature.admin.application.dto.GetUserResult;
import com.adarsh.backend.feature.user.domain.model.Role;
import com.adarsh.backend.feature.user.domain.pagination.PageResult;

public interface GetUserUseCase {
    PageResult<GetUserResult> execute(String keyword,Role role,Boolean enabled,int page,int size);
}
