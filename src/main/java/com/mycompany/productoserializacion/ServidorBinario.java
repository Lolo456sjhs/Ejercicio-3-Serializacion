package com.mycompany.productoserializacion;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;

public class ServidorBinario {

    public static void main(String[] args) throws Exception {

        // Crea el servidor en el puerto 6000
        ServerSocket server = new ServerSocket(6000);
        System.out.println("Servidor Binario escuchando...");

        // Espera conexión del cliente
        Socket socket = server.accept();

        // Flujo para leer datos en formato binario
        DataInputStream in = new DataInputStream(socket.getInputStream());

        ArrayList<Producto> productos = new ArrayList<>();

        // Marca el tiempo inicial
        long inicio = System.currentTimeMillis();

        try {
            while (true) {

                // Lee clave (6 bytes fijos)
                byte[] claveBytes = new byte[6];
                in.readFully(claveBytes);
                String clave = new String(claveBytes).trim();

                // Lee nombre (30 bytes fijos)
                byte[] nombreBytes = new byte[30];
                in.readFully(nombreBytes);
                String nombre = new String(nombreBytes).trim();

                // Lee datos numéricos directamente en binario
                double precio = in.readDouble();
                int cantidad = in.readInt();

                // Lee marca (30 bytes fijos)
                byte[] marcaBytes = new byte[30];
                in.readFully(marcaBytes);
                String marca = new String(marcaBytes).trim();

                // Lee fecha como milisegundos y la convierte a Date
                long fechaLong = in.readLong();
                Date fecha = new Date(fechaLong);

                // Crea el objeto Producto con los datos recibidos
                Producto p = new Producto(clave,nombre,precio,cantidad,marca,fecha);
                productos.add(p);

                System.out.println("Recibido: " + p.toTexto());
            }

        } catch (EOFException e) {
            // Se produce cuando ya no hay más datos que leer
        }

        // Marca el tiempo final
        long fin = System.currentTimeMillis();

        System.out.println("Total productos: " + productos.size());
        System.out.println("Tiempo procesamiento: " + (fin - inicio) + " ms");

        socket.close();
        server.close();
    }
}
