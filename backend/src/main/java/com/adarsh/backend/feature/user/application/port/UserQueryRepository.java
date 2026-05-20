package com.adarsh.backend.feature.user.application.port;

// Used by Admin/Dashboard Use Cases

import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.user.domain.pagination.PageQuery;
import com.adarsh.backend.feature.user.domain.pagination.PageResult;

public interface UserQueryRepository {
    PageResult<User> findAll(PageQuery query);


    PageResult<User> search(UserSearchCriteria criteria, PageQuery pageable);
}
