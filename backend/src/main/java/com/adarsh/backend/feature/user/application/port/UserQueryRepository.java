package com.adarsh.backend.feature.user.application.port;


import com.adarsh.backend.feature.user.domain.model.User;
import com.adarsh.backend.shared.domain.pagination.PageQuery;
import com.adarsh.backend.shared.domain.pagination.PageResult;

import java.util.Optional;


public interface UserQueryRepository {
    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    PageResult<User> search(UserSearchCriteria criteria, PageQuery pageable);
}
