
# API REST con Spring Boot

API REST desarrollada con Spring Boot para gestión de usuarios.

## Tecnologías utilizadas

- Java 21
- Spring Boot 3.5.0
- PostgreSQL
- Lombok
- Maven
- Swagger/OpenAPI

## Requisitos previos

- JDK 17+
- Maven 3.9+
- PostgreSQL

## Proyecto base
https://start.spring.io/

## Variables de entorno necesarias

```bash
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/ebitware
SPRING_DATASOURCE_USERNAME=user123
SPRING_DATASOURCE_PASSWORD=password123
SPRING_SHOW_SQL=true
SPRING_SWAGGER_UI_ENABLED=true
```

## Instalación y ejecución

1. Clonar el repositorio
```bash
git clone <url-repositorio>
```

2. Navegar al directorio del proyecto
```bash
cd crud-springboot
```

3. Compilar el proyecto
```bash
mvn clean install
```

4. Ejecutar la aplicación
```bash
mvn spring-boot:run
```

## Endpoints principales

- POST `/api/v0/usuarios` - Crear usuario
- GET `/api/v0/usuarios` - Listar usuarios
- GET `/api/v0/usuarios/{id}` - Obtener usuario por ID
- PATCH `/api/v0/usuarios/{id}` - Actualizar usuario
- DELETE `/api/v0/usuarios/{id}` - Eliminar usuario

## Documentación API

La documentación de la API está disponible en:
- Swagger UI: http://localhost:8085/swagger-ui/index.html

## Estructura del proyecto

```
crud-springboot/
├── src/main/java/com/ebiteware/crud/
│   ├── controller/     # Controladores REST
│   ├── service/        # Lógica de negocio
│   ├── repository/     # Acceso a datos
│   ├── entity/         # Entidades JPA
│   ├── dto/           # Objetos de transferencia de datos
│   └── config/        # Configuraciones
└── src/test/          # Tests
```

## Base de datos

El proyecto utiliza PostgreSQL. Las tablas necesarias se crean automáticamente al iniciar la aplicación (spring.jpa.hibernate.ddl-auto=update).

## Testing

Para ejecutar los tests:
```bash
mvn test
```