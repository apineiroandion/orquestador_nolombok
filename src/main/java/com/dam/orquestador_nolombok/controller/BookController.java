package com.dam.orquestador_nolombok.controller;

import com.dam.orquestador_nolombok.model.BookRequest;
import com.dam.orquestador_nolombok.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador de libros
 */
@RestController
@RequestMapping("/libros")
public class BookController {

    /**
     * Servicio de libros
     */
    @Autowired
    private BookService bookService;

    /**
     * Obtiene todos los libros
     * @return Lista de libros
     */
    @GetMapping
    public List<BookRequest> getAllLibros() {
        return bookService.findAll();
    }

    /**
     * Obtiene un libro por su ID
     * @param id ID del libro
     * @return Libro
     */
    @GetMapping("/{id}")
    public BookRequest getLibroById(@PathVariable Long id) {
        return bookService.findOneMongo(id);
    }

    /**
     * Obtiene todos los libros en formato XML
     */
    @GetMapping("/xml")
    public void getAllLibrosXml() {
        bookService.writeInXml();
    }

    /**
     * Guarda un libro
     * @param bookRequest Libro a guardar
     */
    @PostMapping
    public void createLibro(@RequestBody BookRequest bookRequest) {
        bookService.saveBook(bookRequest);
    }

    /**
     * Actualiza un libro
     * @param id ID del libro
     * @param bookRequest Libro actualizado
     */
    @PutMapping("/{id}")
    public void updateLibro(@PathVariable Long id, @RequestBody BookRequest bookRequest) {
        bookRequest.setId(id);
        bookService.saveBook(bookRequest);
    }

    /**
     * Elimina un libro por su ID
     * @param id ID del libro
     */
    @DeleteMapping("/{id}")
    public void deleteLibro(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    /**
     * Elimina todos los libros
     */
    @DeleteMapping
    public void deleteAllLibros() {
        bookService.deleteAll();
    }
}