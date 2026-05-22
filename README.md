# Bibliophiles Bazar

Monorepo for Bibliophiles Bazar with a Spring Boot backend and a Vite + React frontend.

## Repository structure

- `backend/` — Spring Boot REST API, Vault-configured datasource, MySQL support.
- `frontend/` — Vite + React TypeScript single-page application.

## Project documentation

- `backend/README.md` — backend setup, commands, Vault configuration, tests.
- `frontend/README.md` — frontend setup, commands, build and lint instructions.

## Quickstart

1. Open `backend/README.md` and `frontend/README.md` for module-specific setup.
2. Start the backend service first, then run the frontend.

## Notes

- `backend/src/main/resources/application.yml` currently uses Spring Cloud Vault for secrets and provides fallback database settings.
- `backend/docker-compose.yml` exists for local containerized development, but the backend service command should be verified before using it in production.

## Contact

For questions about the codebase, contact: adarshmelath1305@gmail.com
