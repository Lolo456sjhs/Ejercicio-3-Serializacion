Ejercicio 3 – Serialización

Proyecto en Java que envía y recibe múltiples objetos Producto mediante sockets, implementando dos métodos:

Texto

Formato:

Clave|Nombre|Precio|Cantidad|Marca|Fecha

Fecha en dd/MM/yyyy.

Binario

Sin separadores, usando tamaños fijos:

Clave: 6 caracteres

Nombre: 30 caracteres

Marca: 30 caracteres

Precio: double

Cantidad: int

Fecha: Timestamp (Epoch)

En ambos casos se mide el tamaño en bytes enviados y el tiempo de procesamiento.
