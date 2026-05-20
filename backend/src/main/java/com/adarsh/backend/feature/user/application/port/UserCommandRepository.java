package com.adarsh.backend.feature.user.application.port;

import java.util.Optional;
import com.adarsh.backend.feature.user.domain.model.User;

// Used by auth/registration Use Cases
public interface UserCommandRepository {
    User save(User user);

    boolean existsByEmail(String email);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);
}