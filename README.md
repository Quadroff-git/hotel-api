# hotel-api

REST API built with Spring Boot as a part of Junior Java Developer interview process at GP Solutions.

## Deploying
```bash
mvn spring-boot:run
```
to deploy with an in-memory database. API available at <localhost:8092/property-view>

## Current state
All requirements specified are met

Additional features added:
- Clean layer separation
- DTO validation using Jakarta Validation
- Exception handling and logging in ControllerAdvice-annotated classes
- Comprehensive unit tests for mappers and services
- OpenAPI 3 documentation generation at runtime with `springdoc` 
  - Swagger UI available at <localhost:8092/property-view/swagger-ui.html>
  - JSON documentation available at <localhost:8092/property-view/v3/api-docs>
  - YAML documentation available at <localhost:8092/property-view/v3/api-docs.yaml>