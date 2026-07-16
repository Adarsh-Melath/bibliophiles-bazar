package com.adarsh.backend.feature.user.application.port;

import com.adarsh.backend.feature.user.domain.model.User;

import java.util.Optional;

// Used by auth/registration Use Cases
public interface UserCommandRepository {
    User save(User user);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}