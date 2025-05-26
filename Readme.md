# Proyectos Prueba 

Este repositorio contiene dos proyectos de ejemplo:

## 1. API REST con Spring Boot 

[Ver documentación del CRUD](./crud-springboot/Readme.md)

API REST desarrollada con Spring Boot para la gestión de usuarios.

### Características principales:
- CRUD completo de usuarios
- PostgreSQL como base de datos
- Documentación con OpenAPI
- Validaciones y manejo de errores

## 2. Procesador de CSV con Node.js 

[Ver documentación del procesador CSV](./cvs-node/Readme.md)

API REST para procesar archivos CSV y almacenarlos en MongoDB.

### Características principales:
- Procesamiento de archivos CSV
- MongoDB como base de datos
- Express para el servidor web

## Requisitos del sistema 🛠

- Docker y Docker Compose
- Java (para el proyecto Spring Boot)
- Node.js(para el proyecto CSV)
- PostgreSQL
- MongoDB

## prerequisitos

1. **Iniciar las bases de datos (mongo y postgresql):**
```bash
docker-compose up -d
```

2. **Iniciar el CRUD de Spring Boot:**
```bash
cd crud-springboot
./mvnw spring-boot:run
```

3. **Iniciar el procesador CSV:**
```bash
cd cvs-node
npm install
npm run dev
```

