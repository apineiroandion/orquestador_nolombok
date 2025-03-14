package com.dam.orquestador_nolombok.client;

import com.dam.orquestador_nolombok.model.BookRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Interfaz que actúa como cliente de la API de libros de la base de datos MongoDB.
 */
@FeignClient(name = "ms-books-mongo", url = "http://localhost:8082")
public interface BookMongoClient {

    /**
     * Obtiene todos los libros de la base de datos MongoDB.
     * @return Lista de libros.
     */
    @GetMapping("/libros")
    List<BookRequest> getAllBooks();

    /**
     * Obtiene un libro de la base de datos MongoDB por su ID.
     * @param id ID del libro.
     * @return Libro.
     */
    @GetMapping("/libros/{id}")
    BookRequest getBookById(@PathVariable("id") Long id);

    /**
     * Guarda un libro en la base de datos MongoDB.
     * @param book Libro a guardar.
     */
    @PostMapping("/libros")
    void saveBook(@RequestBody BookRequest book);

    /**
     * Actualiza un libro de la base de datos MongoDB.
     * @param id ID del libro.
     * @param book Libro actualizado.
     */
    @PutMapping("/libros/{id}")
    void updateBook(@PathVariable("id") Long id, @RequestBody BookRequest book);

    /**
     * Elimina un libro de la base de datos MongoDB por su ID.
     * @param id ID del libro.
     */
    @DeleteMapping("/libros/{id}")
    void deleteBookById(@PathVariable("id") Long id);

    /**
     * Elimina todos los libros de la base de datos MongoDB.
     */
    @DeleteMapping("/libros")
    void deleteAllBooks();
}