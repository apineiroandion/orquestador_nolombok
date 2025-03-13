package com.dam.orquestador_nolombok.controller;

import com.dam.orquestador_nolombok.model.BookRequest;
import com.dam.orquestador_nolombok.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookRequest> getAllLibros() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public BookRequest getLibroById(@PathVariable Long id) {
        return bookService.findOneMongo(id);
    }

    @GetMapping("/xml")
    public String getAllLibrosXml() {
        return bookService.writeInXml();
    }

    @PostMapping
    public void createLibro(@RequestBody BookRequest bookRequest) {
        bookService.saveBook(bookRequest);
    }

    @PutMapping("/{id}")
    public void updateLibro(@PathVariable Long id, @RequestBody BookRequest bookRequest) {
        bookRequest.setId(id);
        bookService.saveBook(bookRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteLibro(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllLibros() {
        bookService.deleteAll();
    }
}