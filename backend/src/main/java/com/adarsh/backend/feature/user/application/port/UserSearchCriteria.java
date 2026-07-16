package com.adarsh.backend.feature.user.application.port;

import com.adarsh.backend.feature.user.domain.model.Role;

public record UserSearchCriteria(
        String searchKeyword,
        Role role,
        Boolean enabled
) {
}