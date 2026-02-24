/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productoserializacion;

import java.io.*;
import java.net.*;
import java.util.Date;

public class ClienteBinario {

    public static void main(String[] args) throws Exception {

        // Se conecta al servidor en el puerto 6000
        Socket socket = new Socket("localhost", 6000);

        // Flujo para enviar datos en formato binario
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        // Productos que se enviarán
        Producto[] productos = {
            new Producto("ABC123","Laptop",12000,5,"Dell",new Date()),
            new Producto("XYZ789","Mouse",300,10,"Logitech",new Date())
        };

        // El flujo en memoria para construir el paquete binario
        ByteArrayOutputStream baos = new ByteArrayOutputStream();   
        DataOutputStream dos = new DataOutputStream(baos);

        // Escribe cada atributo en formato binario
        for (Producto p : productos) {
            dos.write(fixed(p.getClave(),6));          // String fijo 6 bytes
            dos.write(fixed(p.getNombre(),30));        // String fijo 30 bytes
            dos.writeDouble(p.getPrecio());            // Precio 8 bytes
            dos.writeInt(p.getCantidad());             // Cantidad 4 bytes
            dos.write(fixed(p.getMarca(),30));         // Marca fija 30 bytes
            dos.writeLong(p.getFechaReg().getTime());  // Fecha en milisegundos
        }

        // Convierte todo a arreglo de bytes
        byte[] paquete = baos.toByteArray();

        // Envía el paquete al servidor
        out.write(paquete);
        out.flush();

        System.out.println("Bytes enviados (Binario): " + paquete.length);

        socket.close();
    }
    // Método que convierte un String a tamaño fijo de bytes
    private static byte[] fixed(String s, int size) {
        byte[] bytes = new byte[size];          // Arreglo con tamaño fijo
        byte[] sBytes = s.getBytes();           // Convierte el String a bytes
        int len = Math.min(sBytes.length, size); // Evita desbordamiento
        System.arraycopy(sBytes, 0, bytes, 0, len); // Copia los bytes
        return bytes; // Devuelve arreglo rellenado
    }
}
