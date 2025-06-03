------------------------------------------------------------------------------
-- B --Mostrar el número de ventas de cada producto, ordenado de más a menos ventas.
-- Muestra TODOS LOS PRODUCTOS y el número de ventas, incluso si no se han vendido.
-- Si un producto no se ha vendido, debe mostrar 0 ventas.
SELECT 
    p.codigo AS "ID",
    p.nombre as "Producto",
    COUNT(v.producto) as "Número de Ventas"
FROM productos p
LEFT JOIN ventas v ON p.Codigo = v.producto
GROUP BY p.Codigo, p.nombre
ORDER BY COUNT(v.producto) DESC;

-- OPCCION 2
-- Mostrar el número de ventas de cada producto, ordenado de más a menos ventas.
-- Muestra SOLO LOS PRODUCTOS QUE SE HAN VENDIDO.
-- Si un producto no se ha vendido, no debe aparecer en el resultado.
SELECT 
	v.producto AS "ID",
	p.nombre as "Producto",
	count(v.producto) as "Número de Ventas"
FROM ventas v
INNER JOIN productos p on p.codigo = v.producto
GROUP BY v.producto, p.nombre
ORDER BY COUNT(v.producto) DESC;


------------------------------------------------------------------------------
--C - Obtener un informe completo de ventas, indicando el nombre del cajero que realizo la
--venta, nombre y precios de los productos vendidos, y el piso en el que se encuentra la
--máquina registradora donde se realizó la venta.
-- 
SELECT 
v.producto as "ID",
p.nombre as "Producto",
p.precio as "Precio",
c.nombreapels as "Cajero",
m.piso as "Piso de maquina registradora"
FROM ventas v 
INNER JOIN cajeros c on c.codigo = v.cajero
INNER JOIN productos p on p.codigo = v.producto
INNER JOIN maquinas_registradoras m on m.codigo = v.maquina


------------------------------------------------------------------------------
--d Obtener las ventas totales realizadas en cada piso.

SELECT 
m.piso as "Piso de maquina registradora",
count(p.codigo) as "total de productos",
sum(p.precio) as "Total por piso"
FROM ventas v 
INNER JOIN cajeros c on c.codigo = v.cajero
INNER JOIN productos p on p.codigo = v.producto
INNER JOIN maquinas_registradoras m on m.codigo = v.maquina
GROUP BY  m.piso

-------------------------------------------------------------------------------
-- -e. Obtener el código y nombre de cada cajero junto con el importe total de sus ventas.

SELECT 
c.nombreapels as "Cajero",
COALESCE(SUM(p.precio), 0) AS "Total Ventas"
FROM cajeros c 
LEFT JOIN Ventas v on c.codigo = v.cajero
LEFT JOIN productos p on p.codigo = v.producto
GROUP BY c.nombreapels,c.codigo
Order By "Total Ventas" DESC;



--------------------------------------------------------------------------------
-- f. Obtener el código y nombre de aquellos cajeros que hayan realizado ventas en pisos
-- cuyas ventas totales sean inferiores a los 5000 pesos.

SELECT 
c.nombreapels as "Cajero",
c.codigo as "codigo",
Sum(p.precio) as "total de ventas"
FROM ventas v 
INNER JOIN cajeros c on c.codigo = v.cajero
INNER JOIN productos p on p.codigo = v.producto
INNER JOIN maquinas_registradoras m on m.codigo = v.maquina
GROUP BY c.codigo
Having Sum(p.precio) < 5000