package com.adarsh.backend.feature.user.application.interactor;

import com.adarsh.backend.feature.user.application.dto.result.GetUsersResult;
import com.adarsh.backend.feature.user.application.interactor.constant.UserInteractorLogConstants;
import com.adarsh.backend.feature.user.application.usecase.GetUsersUseCase;
import com.adarsh.backend.feature.user.application.port.UserQueryRepository;
import com.adarsh.backend.feature.user.application.port.UserSearchCriteria;
import com.adarsh.backend.feature.user.domain.model.Role;
import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.shared.domain.pagination.PageQuery;
import com.adarsh.backend.shared.domain.pagination.PageResult;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUsersInteractor implements GetUsersUseCase {
    private static final Logger logger = LoggerFactory.getLogger(GetUsersInteractor.class);
    private final UserQueryRepository userQueryRepository;

    @Override
    public PageResult<GetUsersResult> execute(String keyword, Role role, Boolean enabled, int page, int size) {
        logger.info(UserInteractorLogConstants.GET_USERS_REQUEST, page, size);

        PageQuery query = new PageQuery(page, size);
        UserSearchCriteria criteria = new UserSearchCriteria(keyword, role, enabled);

        PageResult<User> domainPage = userQueryRepository.search(criteria, query);
        logger.info(UserInteractorLogConstants.GET_USERS_FETCHED, domainPage.totalElements());

        return domainPage.map(GetUsersResult::fromDomain);
    }
}
