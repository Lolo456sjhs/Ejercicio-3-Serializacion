/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productoserializacion;

import java.io.*;
import java.net.*;
import java.util.Date;

public class ClienteTexto {

    public static void main(String[] args) throws Exception {

        // Se conecta al servidor en localhost puerto 5000
        Socket socket = new Socket("localhost", 5000);

        // Flujo para enviar datos al servidor
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream())
        );
        // Arreglo de productos que se enviarán
        Producto[] productos = {
            new Producto("ABC123","Laptop",12000,5,"Dell",new Date()),
            new Producto("XYZ789","Mouse",300,10,"Logitech",new Date())
        };
        // Se usa para almacenar temporalmente los datos en memoria
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // Convierte cada producto a texto y lo guarda en memoria
        for (Producto p : productos) {
            String linea = p.toTexto() + "\n"; // Cada producto en una línea
            baos.write(linea.getBytes());
        }
        // Convierte todo el contenido acumulado en un arreglo de bytes
        byte[] paquete = baos.toByteArray();

        // Envía los datos al servidor
        out.write(new String(paquete));
        out.flush();

        // Muestra la cantidad de bytes enviados
        System.out.println("Bytes enviados (Texto): " + paquete.length);

        // Cierra la conexión
        socket.close();
    }
}
