package com.mycompany.productoserializacion;

import jakarta.xml.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Entidad Producto adaptada con anotaciones JAXB
 * para serialización/deserialización XML.
 */
@XmlRootElement(name = "producto", namespace = "http://com.mycompany.productoserializacion")
@XmlType(
    namespace = "equipo4",
    propOrder = {"clave", "nombre", "precio", "cantidad", "marca", "fechaReg"}
)
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductoXML {

    private static final String NS = "http://com.mycompany.productoserializacion";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @XmlElement(required = true, namespace = NS)
    private String clave;

    @XmlElement(required = true, namespace = NS)
    private String nombre;

    @XmlElement(required = true, namespace = NS)
    private double precio;

    @XmlElement(required = true, namespace = NS)
    private int cantidad;

    @XmlElement(required = true, namespace = NS)
    private String marca;

    @XmlElement(required = true, namespace = NS)
    private String fechaReg;

    // Constructor vacío requerido por JAXB
    public ProductoXML() {}

    public ProductoXML(String clave, String nombre, double precio,
                       int cantidad, String marca, Date fechaReg) {
        this.clave    = clave;
        this.nombre   = nombre;
        this.precio   = precio;
        this.cantidad = cantidad;
        this.marca    = marca;
        this.fechaReg = sdf.format(fechaReg);
    }

    // Getters
    public String getClave()    { return clave; }
    public String getNombre()   { return nombre; }
    public double getPrecio()   { return precio; }
    public int    getCantidad() { return cantidad; }
    public String getMarca()    { return marca; }
    public String getFechaReg() { return fechaReg; }

    public String toTexto() {
        return String.join("|", clave, nombre,
                String.valueOf(precio), String.valueOf(cantidad),
                marca, fechaReg);
    }
}
