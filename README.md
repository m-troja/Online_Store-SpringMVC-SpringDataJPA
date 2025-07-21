#  Online Store Backend

A modular, production-grade backend system for an online store, built using Java 17 and the Spring Framework ecosystem. This project follows domain-driven design principles and clean architecture by splitting the application into well-defined modules: `core`, `persistence`, and `web`.

Designed for scalability, maintainability, and clarity, this backend serves as a foundation for building e-commerce applications.

---

##  Architecture Overview

The application is divided into the following core modules:

### `core` – Domain & Business Logic
This module defines the essential building blocks of the system:
- Domain entities (e.g., `Product`, `Order`, `User`)
- Business rules and core services
- Interfaces (ports) for infrastructure-level interactions

### `persistence` – Database Layer
Implements the infrastructure layer using Spring Data JPA. Responsibilities include:
- Repository definitions
- JPA entities and mappings
- Transactional operations
- Configuration of database access

### `web` – API & Web Layer
Exposes the application's functionality via RESTful endpoints:
- Spring entry point
- Controllers with request/response DTOs
- Global exception handling and validation

This separation of concerns enables isolated testing, easier onboarding, and more agile development workflows.

---

## Features

- **Modular Design**  
  Cleanly separated layers for domain, persistence, and presentation.

- **Validation and Error Handling**  
  Uses `javax.validation` annotations and centralized exception handling to deliver clean error messages and enforce input integrity.

- **DTO Mapping**  
  Uses custom mappers (or MapStruct if added) to separate internal models from API contracts.

- **Persistence with JPA**  
  Entities are persisted using Spring Data JPA, supporting relational databases.

- **Configuration Abstraction**  
  Externalized configuration supports different profiles for development, testing, and production.

- **Logging & Monitoring**  
  Structured logging via SLF4J and logback is supported for traceability and debugging.

---

## Technologies & Tools

- **Java 17**
- **Spring MVC**
- **Spring Web**
- **Spring Data JPA**
- **Hibernate Validator**
- **Maven**
- **MySQL**

---

## Project Structure

```plaintext
online-store/
├── core/             # Domain logic, services, interfaces
├── persistence/      # JPA repositories, DB config, entities
├── web/              # Controllers, DTOs, entry point
└── README.md
