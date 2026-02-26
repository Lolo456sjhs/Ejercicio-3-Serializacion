package com.mycompany.productoserializacion;

import jakarta.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.XMLConstants;
import java.io.*;
import java.net.*;
import java.util.List;

/**
 * Servidor XML
 * Recibe N instancias de Producto en formato XML (validado con XSD),
 * las deserializa e imprime en consola.
 * Mide y muestra: bytes recibidos y tiempo de procesamiento.
 */
public class ServidorXML {

    public static void main(String[] args) throws Exception {

        // ── 1. Levantar el servidor en puerto 7000 ─────────────────────────
        ServerSocket server = new ServerSocket(7000);
        System.out.println("Servidor XML escuchando en puerto 7000...");

        Socket socket = server.accept();
        System.out.println("Cliente conectado.");

        // ── 2. Leer todos los bytes recibidos ─────────────────────────────
        long inicio = System.currentTimeMillis();

        InputStream is = socket.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesLeidos;
        while ((bytesLeidos = is.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesLeidos);
        }
        byte[] paquete = baos.toByteArray();

        // ── 3. Configurar JAXB con validación XSD ─────────────────────────
        JAXBContext context = JAXBContext.newInstance(ListaProductos.class, ProductoXML.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        // Cargar el esquema XSD para validar al deserializar
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        File xsdFile = new File("producto.xsd");
        if (xsdFile.exists()) {
            Schema schema = sf.newSchema(xsdFile);
            unmarshaller.setSchema(schema); // Activa la validación al deserializar
            System.out.println("Validación XSD activada.");
        } else {
            System.out.println("Advertencia: producto.xsd no encontrado, se omite validación.");
        }

        // ── 4. Deserializar el XML recibido ────────────────────────────────
        ByteArrayInputStream bais = new ByteArrayInputStream(paquete);
        ListaProductos listaRecibida = (ListaProductos) unmarshaller.unmarshal(bais);

        long fin = System.currentTimeMillis();

        // ── 5. Imprimir los productos recibidos ────────────────────────────
        List<ProductoXML> productos = listaRecibida.getProductos();
        System.out.println("\nProductos recibidos:");
        System.out.println("--------------------------------");
        for (ProductoXML p : productos) {
            System.out.println(p.toTexto());
        }

        // ── 6. Mostrar métricas 
        System.out.println("-------------------------------");
        System.out.println("Total productos:         " + productos.size());
        System.out.println("Bytes recibidos (XML):   " + paquete.length);
        System.out.println("Tiempo de procesamiento: " + (fin - inicio) + " ms");
        System.out.println("--------------------------------");

        socket.close();
        server.close();
    }
}
