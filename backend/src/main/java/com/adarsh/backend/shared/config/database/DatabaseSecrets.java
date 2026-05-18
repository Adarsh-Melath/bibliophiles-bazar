package com.adarsh.backend.shared.config.database;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.datasource")
public record DatabaseSecrets(
                String url,
                String username,
                String password) {
}