package com.dam.orquestador_nolombok.repository;

import com.dam.orquestador_nolombok.model.BookRequest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "books")
public class BookListWrapper {

    @XmlElement(name = "book")
    private List<BookRequest> books;

    public BookListWrapper() {
    }

    public BookListWrapper(List<BookRequest> allBooks) {
        this.books = allBooks;
    }


    public List<BookRequest> getBooks() {
        return books;
    }

    public void setBooks(List<BookRequest> books) {
        this.books = books;
    }
}
