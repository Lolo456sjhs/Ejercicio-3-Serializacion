package com.mycompany.productoserializacion;

import jakarta.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.XMLConstants;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Cliente XML
 * Envía N instancias de Producto serializadas en formato XML (validado con XSD)
 * Mide y muestra: bytes enviados y tiempo de procesamiento.
 */
public class ClienteXML {

    public static void main(String[] args) throws Exception {

        // ── 1. Preparar la lista de productos ─────────────────────────────
        List<ProductoXML> lista = new ArrayList<>();
        lista.add(new ProductoXML("ABC123", "Laptop",   12000.0,  5, "Dell",    new Date()));
        lista.add(new ProductoXML("XYZ789", "Mouse",      300.0, 10, "Logitech",new Date()));
        lista.add(new ProductoXML("MNP456", "Teclado",    450.0,  8, "HP",      new Date()));

        ListaProductos listaProductos = new ListaProductos(lista);

        // ── 2. Configurar JAXB con validación XSD ─────────────────────────
        JAXBContext context = JAXBContext.newInstance(ListaProductos.class, ProductoXML.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // XML legible

        // Cargar el esquema XSD para validar antes de enviar
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        // El archivo producto.xsd debe estar en el classpath o ruta relativa
        File xsdFile = new File("producto.xsd");
        if (xsdFile.exists()) {
            Schema schema = sf.newSchema(xsdFile);
            marshaller.setSchema(schema); // Activa la validación al serializar
            System.out.println("Validación XSD activada.");
        } else {
            System.out.println("Advertencia: producto.xsd no encontrado, se omite validación.");
        }

        // ── 3. Serializar la lista a XML en memoria ────────────────────────
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        marshaller.marshal(listaProductos, baos);
        byte[] paquete = baos.toByteArray();

        // Mostrar el XML generado en consola (opcional, útil para depuración)
        System.out.println("XML generado:\n" + new String(paquete));

        // ── 4. Conectar y enviar al servidor XML (puerto 7000) ─────────────
        Socket socket = new Socket("localhost", 7000);
        OutputStream out = socket.getOutputStream();

        long inicio = System.currentTimeMillis();
        out.write(paquete);
        out.flush();
        socket.shutdownOutput(); // Indica al servidor que terminamos de enviar
        long fin = System.currentTimeMillis();

        // ── 5. Mostrar métricas ────────────────────────────────────────────
        System.out.println("--------------------------------");
        System.out.println("Bytes enviados (XML): " + paquete.length);
        System.out.println("Tiempo de envío:      " + (fin - inicio) + " ms");
        System.out.println("--------------------------------");

        socket.close();
    }
}
