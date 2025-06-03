# Levantar contenedor de PostgreSQL
docker-compose up -d postgres

# Verificar que el contenedor está corriendo
docker ps

# Ejecutar script DDL
docker exec -i postgres_db psql -U user123 -d ebitware < 4-postgress-queries/ddl.sql

# Verificar tablas creadas
docker exec -i postgres_db psql -U user123 -d ebitware -c "\dt"

# Ejecutar script de datos semilla
docker exec -i postgres_db psql -U user123 -d ebitware < 4-postgress-queries/seed.sql


#