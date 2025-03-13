package com.dam.orquestador_nolombok.repository;

import com.dam.orquestador_nolombok.model.BookRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Repository;

import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class XmlBookRepository {

    private static final String XML_FILE = "books.xml";
    private final File file;

    public XmlBookRepository() {
        this.file = new File(XML_FILE);
        if (!file.exists()) {
            try {
                file.createNewFile();
//                saveAll(new ArrayList<>());
            } catch (IOException e) {
                throw new RuntimeException("Error creating XML file", e);
            }
        }
    }


    public String toXml (BookListWrapper blw) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(BookListWrapper.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(blw, stringWriter);
        return stringWriter.toString();
    }










//    public List<BookRequest> findAll() {
//        try {
//            JAXBContext context = JAXBContext.newInstance(BookRequest.class);
//            Unmarshaller unmarshaller = context.createUnmarshaller();
//            BookListWrapper wrapper = (BookListWrapper) unmarshaller.unmarshal(file);
//            return wrapper.getBooks();
//        } catch (JAXBException e) {
//            throw new RuntimeException("Error reading XML file", e);
//        }
//    }

//    public BookRequest findOne(Long id) {
//        return findAll().stream().filter(book -> book.getId().equals(id)).findFirst().orElse(null);
//    }

//    public void save(BookRequest bookRequest) {
//        List<BookRequest> books = findAll();
//        books.removeIf(book -> book.getId().equals(bookRequest.getId()));
//        books.add(bookRequest);
//        saveAll(books);
//    }

//    public void deleteById(Long id) {
//        List<BookRequest> books = findAll();
//        books.removeIf(book -> book.getId().equals(id));
//        saveAll(books);
//    }
//
//    public void deleteAll() {
//        saveAll(new ArrayList<>());
//    }
//
//    private void saveAll(List<BookRequest> books) {
//        try {
//            JAXBContext context = JAXBContext.newInstance(BookListWrapper.class);
//            Marshaller marshaller = context.createMarshaller();
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            BookListWrapper wrapper = new BookListWrapper();
//            wrapper.setBooks(books);
//            marshaller.marshal(wrapper, file);
//        } catch (JAXBException e) {
//            throw new RuntimeException("Error writing XML file", e);
//        }
//    }
}
