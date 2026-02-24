/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productoserializacion;

/**
 *
 * @author humbe
 */
import java.text.SimpleDateFormat;
import java.util.Date;

public class Producto {

    private String clave, nombre, marca;
    private double precio;
    private int cantidad;
    private Date fechaReg;

    // Formato único de fecha para serializar y deserializar
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    // Constructor que inicializa todos los datos del producto
    public Producto(String clave, String nombre, double precio,
                    int cantidad, String marca, Date fechaReg) {
        this.clave = clave;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.marca = marca;
        this.fechaReg = fechaReg;
    }

    // Métodos para obtener los valores
    public String getClave() { return clave; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }
    public String getMarca() { return marca; }
    public Date getFechaReg() { return fechaReg; }

    // Convierte el objeto a una línea de texto separada por |
    public String toTexto() {
        return String.join("|",
                clave,
                nombre,
                String.valueOf(precio),
                String.valueOf(cantidad),
                marca,
                sdf.format(fechaReg) // Convierte la fecha a texto
        );
    }

    // Crea un objeto Producto a partir de una línea de texto
    public static Producto fromTexto(String texto) throws Exception {
        String[] p = texto.split("\\|"); // Divide la cadena usando "|"

        return new Producto(
                p[0],
                p[1],
                Double.parseDouble(p[2]), // Convierte texto a double
                Integer.parseInt(p[3]),   // Convierte texto a int
                p[4],
                sdf.parse(p[5])           // Convierte texto a fecha
        );
    }
}
