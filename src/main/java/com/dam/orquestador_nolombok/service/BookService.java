package com.dam.orquestador_nolombok.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dam.orquestador_nolombok.client.BookMongoClient;
import com.dam.orquestador_nolombok.client.BookPostgresClient;
import com.dam.orquestador_nolombok.model.BookRequest;
import com.dam.orquestador_nolombok.repository.XmlBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private static final Logger errorLogger = LoggerFactory.getLogger("ERROR_LOGGER");
    private static final Logger activityLogger = LoggerFactory.getLogger("ACTIVITY_LOGGER");

    private final BookMongoClient bookMongoClient;
    private final BookPostgresClient bookPostgresClient;
    private final XmlBookRepository xmlBookRepository;

    public BookService(BookMongoClient bookMongoClient, BookPostgresClient bookPostgresClient, XmlBookRepository xmlBookRepository) {
        try {
            this.bookMongoClient = bookMongoClient;
            this.bookPostgresClient = bookPostgresClient;
            this.xmlBookRepository = xmlBookRepository;

            activityLogger.info("Instancia de BookService creada");
        } catch (Exception e) {
            errorLogger.error("Error al instanciar BookService", e);
            throw e;
        }
    }

    public void saveBookInMongo(BookRequest bookRequest) {
        try {
            bookMongoClient.saveBook(bookRequest);
            activityLogger.info("Libro guardado en MongoDB" + bookRequest.getId() + " " + bookRequest.getTitulo() + " " + bookRequest.getPreco());
        } catch (Exception e) {
            errorLogger.error("Error al guardar libro en MongoDB", e);
        }
    }

    public void saveBookInPostgres(BookRequest bookRequest) {
        try {
            bookPostgresClient.saveBook(bookRequest);
            activityLogger.info("Libro guardado en Postgres" + bookRequest.getId() + " " + bookRequest.getTitulo() + " " + bookRequest.getPreco());
        } catch (Exception e) {
            errorLogger.error("Error al guardar libro en Postgres", e);
        }
    }

    public void saveBook(BookRequest bookRequest) {
        try {
            saveBookInMongo(bookRequest);
            saveBookInPostgres(bookRequest);
            activityLogger.info("Libro guardo en MongoDB y Postgres" + bookRequest.getId() + " " + bookRequest.getTitulo() + " " + bookRequest.getPreco());
        } catch (Exception e) {
            errorLogger.error("Error al guardar libro", e);
        }
    }

    public void deleteById(Long id) {
        try {
            bookMongoClient.deleteBookById(id);
            bookPostgresClient.deleteBookById(id);
            activityLogger.info("Libro eliminado" + id);
        } catch (Exception e) {
            errorLogger.error("Error al eliminar libro", e);
        }
    }

    public void deleteAll() {
        try {
            bookMongoClient.deleteAllBooks();
            bookPostgresClient.deleteAllBooks();
            activityLogger.info("Todos los libros eliminados");
        } catch (Exception e) {
            errorLogger.error("Error al eliminar libros", e);
        }
    }

    public BookRequest findOneMongo(Long id) {
        try {
            activityLogger.info("Buscando libro en mongo db" + id);
            return bookMongoClient.getBookById(id);
        } catch (Exception e) {
            errorLogger.error("Error al buscar libro en MongoDB", e);
            throw new RuntimeException(e);
        }
    }

    public List<BookRequest> findAll() {
        try {
            activityLogger.info("Buscando todos los libros");
            return bookMongoClient.getAllBooks();
        } catch (Exception e) {
            errorLogger.error("Error al buscar libros", e);
            throw new RuntimeException(e);
        }
    }

    public void writeInXml() {
        xmlBookRepository.toXml(findAll());
        activityLogger.info("Libros exportados a XML");
    }

}
