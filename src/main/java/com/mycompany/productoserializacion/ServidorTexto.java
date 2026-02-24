/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productoserializacion;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServidorTexto {

    public static void main(String[] args) throws Exception {

        // Crea el servidor en el puerto 5000
        ServerSocket server = new ServerSocket(5000);
        System.out.println("Servidor Texto escuchando...");

        // Espera a que un cliente se conecte
        Socket socket = server.accept();

        // Flujo para recibir datos del cliente
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );

        // Lista donde se almacenarán los productos recibidos
        ArrayList<Producto> productos = new ArrayList<>();

        // Marca el tiempo inicial para medir procesamiento
        long inicio = System.currentTimeMillis();

        String linea;
        // Lee cada línea enviada por el cliente
        while ((linea = in.readLine()) != null) {

            // Convierte la línea de texto en objeto Producto
            Producto p = Producto.fromTexto(linea);
            productos.add(p); // Guarda el producto en la lista
            System.out.println("Recibido: " + p.toTexto());
        }

        // Muestra el tiempo final
        long fin = System.currentTimeMillis();

        // Resultados
        System.out.println("Total productos: " + productos.size());
        System.out.println("Tiempo procesamiento: " + (fin - inicio) + " ms");

        // Cierra conexiones
        socket.close();
        server.close();
    }
}
