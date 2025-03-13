package com.dam.orquestador_nolombok.client;

import com.dam.orquestador_nolombok.model.BookRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ms-books-postgres", url = "http://localhost:8081")
public interface BookPostgresClient {

    @GetMapping("/libros")
    List<BookRequest> getAllBooks();

    @GetMapping("/libros/{id}")
    BookRequest getBookById(@PathVariable("id") Long id);

    @PostMapping("/libros")
    void saveBook(@RequestBody BookRequest book);

    @PutMapping("/libros/{id}")
    void updateBook(@PathVariable("id") Long id, @RequestBody BookRequest book);

    @DeleteMapping("/libros/{id}")
    void deleteBookById(@PathVariable("id") Long id);

    @DeleteMapping("/libros")
    void deleteAllBooks();
}
