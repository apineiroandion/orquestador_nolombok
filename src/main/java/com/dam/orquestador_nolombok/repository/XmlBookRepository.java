package com.dam.orquestador_nolombok.repository;

import com.dam.orquestador_nolombok.model.BookRequest;
import org.springframework.stereotype.Repository;
import javax.xml.stream.XMLOutputFactory;
import java.io.*;
import javax.xml.stream.XMLStreamWriter;
import java.util.List;

/**
 * Repositorio de libros en XML
 */
@Repository
public class XmlBookRepository {

    /**
     * Rutal del archivo XML
     */
    private static final String XML_FILE = "books.xml";
    private final File file;

    /**
     * Constructor de la clase
     */
    public XmlBookRepository() {
        this.file = new File(XML_FILE);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Error creating XML file", e);
            }
        }
    }

    /**
     * Escribe los libros en formato XML
     * @param blw Lista de libros
     */
    public void toXml (List<BookRequest> blw) {
        try{
            FileOutputStream fos = new FileOutputStream(file);
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            StringWriter stringWriter = new StringWriter();
            XMLStreamWriter writer = factory.createXMLStreamWriter(stringWriter);

            writer.writeStartDocument("1.0");
            writer.writeStartElement("books");
            for (BookRequest bookRequest : blw) {
                writer.writeStartElement("book");
                writer.writeAttribute("id", bookRequest.getId().toString());
                writer.writeStartElement("titulo");
                writer.writeCharacters(bookRequest.getTitulo());
                writer.writeEndElement();
                writer.writeStartElement("preco");
                writer.writeCharacters(bookRequest.getPreco().toString());
                writer.writeEndElement();
                writer.writeEndElement();
            }
            writer.writeEndElement();
            writer.writeEndDocument();
            writer.flush();
            writer.close();
            //guardo en books.xml
            System.out.println(stringWriter);
            fos.write(stringWriter.toString().getBytes());
            fos.close();
        }catch (Exception e){
            throw new RuntimeException("Error writing XML file", e);
        }
    }
}
