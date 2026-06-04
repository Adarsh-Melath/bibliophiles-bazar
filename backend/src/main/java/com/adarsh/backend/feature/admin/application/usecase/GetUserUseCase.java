package com.adarsh.backend.feature.admin.application.usecase;

import com.adarsh.backend.feature.admin.application.dto.GetUserResult;
import com.adarsh.backend.feature.user.domain.pagination.PageResult;

public interface GetUserUseCase {
    PageResult<GetUserResult> execute(int page,int size);
}
