# Ejercicio 3 y 4 – Serialización y Esquema XML

Proyecto en Java que envía y recibe múltiples objetos **Producto** mediante sockets, implementando dos métodos de serialización (Texto y Binario) y esquema XML con validación XSD.

---

## Ejercicio 3 – Serialización

### Serialización en Texto
Formato utilizado:
Clave|Nombre|Precio|Cantidad|Marca|Fecha

- Separador: `|`
- Fecha en formato: `dd/MM/yyyy`

---

### Serialización en Binario
Sin separadores, utilizando tamaños fijos:
- Clave: 6 caracteres  
- Nombre: 30 caracteres  
- Marca: 30 caracteres  
- Precio: double  
- Cantidad: int  
- Fecha: Timestamp (Epoch)

---

## Ejercicio 4 – Esquema XML + XSD

Proyecto en Java que envía y recibe múltiples objetos **Producto** en formato XML, validados contra un esquema XSD antes de ser enviados y al ser recibidos.

### Formato XML
```xml
<productos>
    <producto>
        <clave>ABC123</clave>
        <nombre>Laptop</nombre>
        <precio>12000.0</precio>
        <cantidad>5</cantidad>
        <marca>Dell</marca>
        <fechaReg>25/02/2026</fechaReg>
    </producto>
</productos>
```

### Validación XSD
- El esquema `producto.xsd` define la estructura válida de cada producto
- El Cliente valida el XML antes de enviarlo
- El Servidor valida el XML al recibirlo
- Conexión mediante Sockets en puerto 7000

---

## Medición
En todos los casos se mide:
- Tamaño en bytes enviados/recibidos
- Tiempo de procesamiento

---

Equipo 4
