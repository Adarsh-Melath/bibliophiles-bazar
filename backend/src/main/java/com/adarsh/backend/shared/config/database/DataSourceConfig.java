package com.adarsh.backend.shared.config.database;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableConfigurationProperties(DatabaseSecrets.class)
public class DataSourceConfig {
    private final DatabaseSecrets secrets;

    public DataSourceConfig(DatabaseSecrets secrets) {
        this.secrets = secrets;
    }

    @Bean
    public javax.sql.DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();

        // These values are cleanly populated by Vault at boot runtime!
        dataSource.setJdbcUrl(secrets.getUrl());
        dataSource.setUsername(secrets.getUsername());
        dataSource.setPassword(secrets.getPassword());

        if (secrets.getDriverClassName() != null && !secrets.getDriverClassName().isBlank()) {
            dataSource.setDriverClassName(secrets.getDriverClassName());
        }

        return dataSource;
    }
}
