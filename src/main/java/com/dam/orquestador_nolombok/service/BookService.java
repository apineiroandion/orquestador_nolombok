package com.dam.orquestador_nolombok.service;

import com.dam.orquestador_nolombok.client.BookMongoClient;
import com.dam.orquestador_nolombok.client.BookPostgresClient;
import com.dam.orquestador_nolombok.model.BookRequest;
import com.dam.orquestador_nolombok.repository.BookListWrapper;
import com.dam.orquestador_nolombok.repository.XmlBookRepository;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.List;

@Service
public class BookService {
    private final BookMongoClient bookMongoClient;
    private final BookPostgresClient bookPostgresClient;
    private final XmlBookRepository xmlBookRepository;

    public BookService(BookMongoClient bookMongoClient, BookPostgresClient bookPostgresClient, XmlBookRepository xmlBookRepository) {
        this.bookMongoClient = bookMongoClient;
        this.bookPostgresClient = bookPostgresClient;
        this.xmlBookRepository = xmlBookRepository;
    }

    public void saveBookInMongo(BookRequest bookRequest) {
        bookMongoClient.saveBook(bookRequest);
    }

    public void saveBookInPostgres(BookRequest bookRequest) {
        bookPostgresClient.saveBook(bookRequest);
    }

//    public void saveBookInXml(BookRequest bookRequest) {
//        xmlBookRepository.save(bookRequest);
//    }

    public void saveBook(BookRequest bookRequest) {
        saveBookInMongo(bookRequest);
        saveBookInPostgres(bookRequest);
//        saveBookInXml(bookRequest);
    }

    public void deleteById(Long id) {
        bookMongoClient.deleteBookById(id);
        bookPostgresClient.deleteBookById(id);
//        xmlBookRepository.deleteById(id);
    }

    public void deleteAll() {
        bookMongoClient.deleteAllBooks();
        bookPostgresClient.deleteAllBooks();
//        xmlBookRepository.deleteAll();
    }

    public BookRequest findOneMongo(Long id) {
        return bookMongoClient.getBookById(id);
    }

    public List<BookRequest> findAll() {
        return bookMongoClient.getAllBooks();
    }

    public BookListWrapper findAll2() {
        return new BookListWrapper(bookMongoClient.getAllBooks());
    }

    public String writeInXml() {
        try {
            return xmlBookRepository.toXml(findAll2());
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}
