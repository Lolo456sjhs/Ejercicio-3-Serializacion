package com.mycompany.productoserializacion;

import jakarta.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase envolvente (wrapper) para serializar una lista de ProductoXML
 * como elemento raíz <productos> en el XML.
 */
@XmlRootElement(name = "productos", namespace = "http://com.mycompany.productoserializacion")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaProductos {

    @XmlElement(name = "producto", namespace = "http://com.mycompany.productoserializacion")
    private List<ProductoXML> productos = new ArrayList<>();

    // Constructor vacío requerido por JAXB
    public ListaProductos() {}

    public ListaProductos(List<ProductoXML> productos) {
        this.productos = productos;
    }

    public List<ProductoXML> getProductos() {
        return productos;
    }
}
