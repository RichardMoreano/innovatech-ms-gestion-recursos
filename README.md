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

## Ejemplos de Uso

```http
POST /api/recursos
```

**Request Body (JSON):**

```json
{
  "nombre": "Juan",
  "apellido": "Pérez",
  "email": "juan.perez@innovatech.cl",
  "rol": "DESARROLLADOR",
  "horasSemana": 40
}
```

### Crear otro Recurso

```json
{
  "nombre": "Camila",
  "apellido": "González",
  "email": "camila.gonzalez@innovatech.cl",
  "rol": "QA",
  "horasSemana": 35
}
```

### Crear otro Recurso

```json
{
  "nombre": "Matías",
  "apellido": "Rojas",
  "email": "matias.rojas@innovatech.cl",
  "rol": "DEVOPS",
  "horasSemana": 45
}
```

## Patrones implementados
- Repository Pattern
- Factory Method
- DTO Pattern
- Layered Architecture
- JWT Authentication

## Cómo ejecutar

```bash
./mvnw spring-boot:run