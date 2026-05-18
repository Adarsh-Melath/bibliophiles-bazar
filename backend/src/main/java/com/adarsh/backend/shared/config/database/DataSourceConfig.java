package com.adarsh.backend.shared.config.database;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(DatabaseSecrets.class)
public class DataSourceConfig {

    private final DatabaseSecrets secrets;

    public DataSourceConfig(DatabaseSecrets secrets) {
        this.secrets = secrets;
    }

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();

        // These values are cleanly populated by Vault at boot runtime!
        dataSource.setJdbcUrl(secrets.url());
        dataSource.setUsername(secrets.username());
        dataSource.setPassword(secrets.password());
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        return dataSource;
    }
}