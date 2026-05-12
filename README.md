# Innovatech Servicio Recurso

Microservicio de Gestión de Recursos y Colaboración - Innovatech Solutions

## Tecnologías
- Spring Boot 4.0.6
- Java 25
- JPA + PostgreSQL
- JWT Authentication
- Lombok

## Endpoints Principales

| Método | Endpoint                          | Descripción                          |
|--------|-----------------------------------|--------------------------------------|
| POST   | `/api/recursos`                   | Crear nuevo recurso                  |
| GET    | `/api/recursos`                   | Listar todos los recursos            |
| GET    | `/api/recursos/disponibilidad/{estado}` | Listar por disponibilidad     |

## Patrones implementados
- Repository Pattern
- Factory Method
- DTO Pattern
- Layered Architecture
- JWT Authentication

## Cómo ejecutar

```bash
./mvnw spring-boot:run