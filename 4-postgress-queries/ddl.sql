-- Crear tabla de cajeros
CREATE TABLE cajeros (
    Codigo SERIAL PRIMARY KEY,
    NombreApels VARCHAR(255) NOT NULL
);

-- Crear tabla de productos
CREATE TABLE productos (
    Codigo SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL, 
    precio DECIMAL(10,2) NOT NULL 
);

-- Crear tabla de máquinas registradoras
CREATE TABLE maquinas_registradoras (
    codigo SERIAL PRIMARY KEY,    
    piso INTEGER NOT NULL
    
);

-- Crear tabla de ventas
CREATE TABLE ventas (
    cajero INTEGER NOT NULL,
    producto INTEGER NOT NULL,
    maquina INTEGER NOT NULL,
    
    CONSTRAINT fk_cajero 
        FOREIGN KEY (cajero) 
        REFERENCES cajeros (Codigo),
    CONSTRAINT fk_producto 
        FOREIGN KEY (producto) 
        REFERENCES productos (Codigo),
    CONSTRAINT fk_maquina 
        FOREIGN KEY (maquina) 
        REFERENCES maquinas_registradoras (codigo)
);

-- Crear índices para mejorar el rendimiento
CREATE INDEX idx_ventas_cajero ON ventas(cajero);
CREATE INDEX idx_ventas_producto ON ventas(producto);
CREATE INDEX idx_ventas_maquina ON ventas(maquina);

