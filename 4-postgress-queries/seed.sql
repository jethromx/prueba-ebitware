-- Insertar cajeros
INSERT INTO cajeros (NombreApels) VALUES
    ('Juan Pérez'),
    ('María García'),
    ('Pedro López'),
    ('Ana Martínez'),
    ('Laura Fernández'),
    ('Luis Gómez'),
    ('Sofía Torres'),
    ('Carlos Rodríguez');

-- Insertar productos
INSERT INTO productos (nombre, precio) VALUES
    ('Laptop Dell', 8999.99),
    ('Mouse Inalámbrico', 29.99),
    ('Teclado Mecánico', 89.99),
    ('Monitor 24"', 199.99),
    ('Auriculares Gaming', 59.99),
    ('Webcam HD', 49.99),
    ('Disco SSD 500GB', 79.99),
    ('Memoria RAM 16GB', 89.99);

-- Insertar máquinas registradoras
INSERT INTO maquinas_registradoras (piso) VALUES
    (1),
    (1),
    (2),
    (2),
    (3);

-- Insertar ventas
INSERT INTO ventas (cajero, producto, maquina) VALUES
    (1, 1, 1),
    (1, 2, 1 ),
    (2, 3, 2 ),
    (2, 3, 2),
    (3, 4, 3),
    (3, 5, 3),
    (4, 6, 4),
    (4, 7, 4),
    (5, 8, 5),
    (5, 1, 5);
