package com.dam.orquestador_nolombok.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dam.orquestador_nolombok.client.BookMongoClient;
import com.dam.orquestador_nolombok.client.BookPostgresClient;
import com.dam.orquestador_nolombok.model.BookRequest;
import com.dam.orquestador_nolombok.repository.XmlBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio de libros
 */
@Service
public class BookService {
    // Loggers
    private static final Logger errorLogger = LoggerFactory.getLogger("ERROR_LOGGER");
    private static final Logger activityLogger = LoggerFactory.getLogger("ACTIVITY_LOGGER");

    /**
     * Cliente de MongoDB
     */
    private final BookMongoClient bookMongoClient;

    /**
     * Cliente de Postgres
     */
    private final BookPostgresClient bookPostgresClient;

    /**
     * Repositorio de libros en XML
     */
    private final XmlBookRepository xmlBookRepository;

    /**
     * Constructor de la clase
     * @param bookMongoClient Cliente de MongoDB
     * @param bookPostgresClient Cliente de Postgres
     * @param xmlBookRepository Repositorio de libros en XML
     */
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

    /**
     * Guarda un libro en MongoDB
     * @param bookRequest Libro a guardar
     */
    public void saveBookInMongo(BookRequest bookRequest) {
        try {
            bookMongoClient.saveBook(bookRequest);
            activityLogger.info("Libro guardado en MongoDB{} {} {}", bookRequest.getId(), bookRequest.getTitulo(), bookRequest.getPreco());
        } catch (Exception e) {
            errorLogger.error("Error al guardar libro en MongoDB", e);
        }
    }

    /**
     * Guarda un libro en Postgres
     * @param bookRequest Libro a guardar
     */
    public void saveBookInPostgres(BookRequest bookRequest) {
        try {
            bookPostgresClient.saveBook(bookRequest);
            activityLogger.info("Libro guardado en Postgres{} {} {}", bookRequest.getId(), bookRequest.getTitulo(), bookRequest.getPreco());
        } catch (Exception e) {
            errorLogger.error("Error al guardar libro en Postgres", e);
        }
    }

    /**
     * Guarda un libro en MongoDB y Postgres
     * @param bookRequest Libro a guardar
     */
    public void saveBook(BookRequest bookRequest) {
        try {
            saveBookInMongo(bookRequest);
            saveBookInPostgres(bookRequest);
            activityLogger.info("Libro guardo en MongoDB y Postgres{} {} {}", bookRequest.getId(), bookRequest.getTitulo(), bookRequest.getPreco());
        } catch (Exception e) {
            errorLogger.error("Error al guardar libro", e);
        }
    }

    /**
     * Actualiza un libro en MongoDB
     * @param id ID del libro
     */
    public void deleteById(Long id) {
        try {
            bookMongoClient.deleteBookById(id);
            bookPostgresClient.deleteBookById(id);
            activityLogger.info("Libro eliminado{}", id);
        } catch (Exception e) {
            errorLogger.error("Error al eliminar libro", e);
        }
    }

    /**
     * Elimina todos los libros
     */
    public void deleteAll() {
        try {
            bookMongoClient.deleteAllBooks();
            bookPostgresClient.deleteAllBooks();
            activityLogger.info("Todos los libros eliminados");
        } catch (Exception e) {
            errorLogger.error("Error al eliminar libros", e);
        }
    }

    /**
     * Busca un libro en MongoDB
     * @param id ID del libro
     * @return Libro
     */
    public BookRequest findOneMongo(Long id) {
        try {
            activityLogger.info("Buscando libro en mongo db{}", id);
            return bookMongoClient.getBookById(id);
        } catch (Exception e) {
            errorLogger.error("Error al buscar libro en MongoDB", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Busca todos los libros
     * @return Lista de libros
     */
    public List<BookRequest> findAll() {
        try {
            activityLogger.info("Buscando todos los libros");
            return bookMongoClient.getAllBooks();
        } catch (Exception e) {
            errorLogger.error("Error al buscar libros", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Exporta los libros a XML
     */
    public void writeInXml() {
        xmlBookRepository.toXml(findAll());
        activityLogger.info("Libros exportados a XML");
    }

}
