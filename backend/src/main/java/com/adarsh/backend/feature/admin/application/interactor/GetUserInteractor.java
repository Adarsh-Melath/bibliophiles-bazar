package com.adarsh.backend.feature.admin.application.interactor;

import org.springframework.stereotype.Service;

import com.adarsh.backend.feature.admin.application.dto.GetUserResult;
import com.adarsh.backend.feature.admin.application.usecase.GetUserUseCase;
import com.adarsh.backend.feature.user.application.port.UserQueryRepository;
import com.adarsh.backend.feature.user.application.port.UserSearchCriteria;
import com.adarsh.backend.feature.user.domain.model.Role;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.user.domain.pagination.PageQuery;
import com.adarsh.backend.feature.user.domain.pagination.PageResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetUserInteractor implements GetUserUseCase {

    private final UserQueryRepository userQueryRepository;

    @Override
    public PageResult<GetUserResult> execute(String keyword, Role role, Boolean enabled, int page, int size) {
        PageQuery query = new PageQuery(page, size);
        UserSearchCriteria criteria = new UserSearchCriteria(keyword, role, enabled);

        PageResult<User> domainPage = userQueryRepository.search(criteria, query);

        return domainPage.map(GetUserResult::fromDomain);
    }
}
