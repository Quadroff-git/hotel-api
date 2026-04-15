# hotel-api

REST API built with Spring Boot as a part of Junior Java Developer interview process at GP Solutions.

## Deploying
```bash
mvn spring-boot:run
```
to deploy with an in-memory database. API available at `<localhost:8092/property-view>`

### Switching to a different RDBMS
The app is configured to allow for easy database switching and ships with a PostgresSQL profile.
Simply run
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=postgres
```
or change `spring.profiles.default` to `postgres` in `application.yaml` to switch the application to PostgreSQL.
See `application-postgres.yaml` for connection configuration.

## Current state
All requirements specified are met

Additional features added:
- Clean layer separation
- DTO validation using Jakarta Validation (validation is minimal, mostly checking for required fields and common formats)
- Exception handling and logging in a ControllerAdvice-annotated class
- Comprehensive unit tests for mappers and service layer
- OpenAPI 3 documentation generation at runtime with `springdoc` 
  - Swagger UI available at `<localhost:8092/property-view/swagger-ui.html>`
  - JSON documentation available at `<localhost:8092/property-view/v3/api-docs>`
  - YAML documentation available at `<localhost:8092/property-view/v3/api-docs.yaml>`
- Support for quick RDBMS switching: run the app with PostgreSQL support by adding one command-line parameter
- Database schema management with Liquibase