# Backend

Spring Boot backend for Bibliophiles Bazar.

## Overview

The backend provides REST APIs for the application, stores data in MySQL, and loads secret configuration from HashiCorp Vault.

## Requirements

- Java 17 JDK
- Maven 3.8+
- Docker & Docker Compose (recommended for local development)
- Vault (for secrets management)

## Local development

### Recommended: manual backend startup

The current codebase is easiest to run locally using the Spring Boot Maven plugin.

```bash
cd backend
mvn spring-boot:run
```

If the application fails to start because Vault is unavailable, the fallback values in `src/main/resources/application.yml` are used for local development.

### Docker Compose

A Compose file is available at `backend/docker-compose.yml`, but the backend startup command should be reviewed before relying on it. If you want to use containerized local development, verify that the backend service command is correct and that Vault and MySQL containers are seeded with the expected values.

## Vault and database configuration

The backend configuration in `src/main/resources/application.yml` uses Spring Cloud Vault with the following properties:

- `spring.datasource.url`
- `spring.datasource.username`
- `spring.datasource.password`
- `spring.datasource.driver-class-name`

Vault values are loaded from the configured `secret/bibliophiles-bazar` context. The application also provides fallback values for database connection properties when Vault secrets are not present.

## Build

```bash
cd backend
mvn clean package
```

## Tests

```bash
cd backend
mvn test
```

## Notes

- The backend default port is `8080`.
- Ensure the database and Vault are available before starting the service for full functionality.
