package com.adarsh.backend.feature.user.application.port;


import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.feature.user.domain.pagination.PageQuery;
import com.adarsh.backend.feature.user.domain.pagination.PageResult;

    
public interface UserQueryRepository {
    PageResult<User> search(UserSearchCriteria criteria, PageQuery pageable);
}
