# Innovatech Solutions - MS Gestión de Recursos

Microservicio basado en Spring Boot encargado de la administración integral de capital humano, roles, cargas horarias y disponibilidad de los colaboradores dentro del ecosistema Innovatech.

## Resumen Técnico

- **Nombre del Módulo:** `innovatech-ms-gestion-recursos` (Contenedor: `ms-gestion-recursos`)
- **Tecnologías Core:** Java 17, Spring Boot 3.x, Spring Data JPA, Hibernate, PostgreSQL.
- **Puerto Base (Host):** `8086` (Consumido internamente por el BFF mediante resolución DNS de Docker).
- **Patrones de Diseño:** Layered Architecture, Data Transfer Object (DTO), Repository Pattern.

---

## Estructura Arquitectónica

- `controller/RecursoController.java` — Controlador REST expuesto bajo la ruta unificada `/api/v2/recursos`.
- `service/` — Capa de negocio (interfaz `RecursoService` e implementación `RecursoServiceImpl`).
- `repository/RecursoRepository.java` — Abstracción de datos con métodos derivados como `findByDisponibilidad()`.
- `model/Recurso.java` — Entidad JPA mapeada a la tabla relacional `recursos`.
- `dto/` — Contratos de validación de entrada y salida (`RecursoRequestDTO` y `RecursoResponseDTO`).

---

## Contratos de API (Endpoints)

**Ruta Base:** `/api/v2/recursos`

| Método | Endpoint | Descripción | Estado |
|----------|----------|-------------|---------|
| `POST` | `/api/v2/recursos` | Registra e inicializa un nuevo colaborador. | `201 Created` |
| `GET` | `/api/v2/recursos` | Recupera el listado total de personal. | `200 OK` |
| `GET` | `/api/v2/recursos/{id}` | Recupera el perfil de un recurso por su ID. | `200 OK` |
| `PUT` | `/api/v2/recursos/{id}` | Actualiza completamente los campos de un recurso. | `200 OK` |
| `DELETE` | `/api/v2/recursos/{id}` | Elimina el registro del colaborador. | `204 No Content` |
| `GET` | `/api/v2/recursos/disponibilidad/{estado}` | Filtra recursos por disponibilidad. | `200 OK` |
| `PUT` | `/api/v2/recursos/{id}/disponibilidad?disponibilidad={V}` | Actualiza rápidamente la disponibilidad. | `200 OK` |

### Estados de Disponibilidad

- `DISPONIBLE`
- `OCUPADO`
- `VACACIONES`

---

## Estructura de Payloads

### Crear Recurso

**Solicitud**

```json
{
  "nombre": "Juan",
  "apellido": "Pérez",
  "email": "juan.perez@innovatech.cl",
  "rol": "DESARROLLADOR",
  "disponibilidad": "DISPONIBLE",
  "horasSemana": 40
}
```

### Respuesta Exitosa

```json
{
  "id": 1,
  "nombre": "Juan",
  "apellido": "Pérez",
  "email": "juan.perez@innovatech.cl",
  "rol": "DESARROLLADOR",
  "disponibilidad": "DISPONIBLE",
  "fechaContratacion": "2026-06-18T23:20:00",
  "horasSemana": 40
}
```

### Validaciones

El servicio utiliza Jakarta Validation mediante:

- `@NotBlank`
- `@Email`
- `@Min`

Las solicitudes inválidas retornan automáticamente:

```http
400 Bad Request
```

---

## Parámetros de Configuración

Configure las siguientes variables de entorno para enlazar el servicio con PostgreSQL:

```properties
SPRING_DATASOURCE_URL=jdbc:postgresql://postgres-db:5432/innovatech_db
SPRING_DATASOURCE_USERNAME=admin
SPRING_DATASOURCE_PASSWORD=supersecretpassword
SERVER_PORT=8086
```

---

# Instrucciones de Ejecución

## Opción 1: Desarrollo Local (Maven Wrapper)

```bash
cd innovatech-ms-gestion-recursos

./mvnw spring-boot:run
```

---

## Opción 2: Empaquetado y Docker

### Compilar aplicación

```bash
./mvnw clean package -DskipTests
```

### Construir imagen Docker

```bash
docker build -t innovatech-ms-gestion-recursos .
```

### Ejecutar contenedor

```bash
docker run -p 8086:8086 \
  -e SPRING_DATASOURCE_URL="jdbc:postgresql://host.docker.internal:5432/innovatech_db" \
  -e SPRING_DATASOURCE_USERNAME="admin" \
  -e SPRING_DATASOURCE_PASSWORD="supersecretpassword" \
  innovatech-ms-gestion-recursos
```

---

# Estrategia de Testing

Las pruebas unitarias permiten validar:

- Reglas de asignación horaria.
- Validaciones de negocio.
- Operaciones CRUD.
- Gestión de disponibilidad.
- Persistencia y consultas JPA.

### Ejecutar pruebas

```bash
./mvnw test
```