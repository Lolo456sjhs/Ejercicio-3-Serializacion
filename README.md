# Ejercicio 3 – Serialización

Proyecto en Java que envía y recibe múltiples objetos **Producto** mediante sockets, implementando dos métodos de serialización: Texto y Binario.
---
## Serialización en Texto

Formato utilizado:

Clave|Nombre|Precio|Cantidad|Marca|Fecha

- Separador: `|`
- Fecha en formato: `dd/MM/yyyy`
---
## Serialización en Binario

Sin separadores, utilizando tamaños fijos:

- Clave: 6 caracteres  
- Nombre: 30 caracteres  
- Marca: 30 caracteres  
- Precio: double  
- Cantidad: int  
- Fecha: Timestamp (Epoch)
---
## Medición

En ambos casos se mide:
- Tamaño en bytes enviados
- Tiempo de procesamiento

Equipo 4
