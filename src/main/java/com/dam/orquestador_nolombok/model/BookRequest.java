package com.dam.orquestador_nolombok.model;

/**
 * Clase que representa un libro.
 */
public class BookRequest {

    /**
     * ID del libro.
     */
    private Long id;

    /**
     * Título del libro.
     */
    private final String titulo;

    /**
     * Precio del libro.
     */
    private final Double preco;

    /**
     * Constructor con parámetros.
     * @param id ID del libro.
     * @param titulo Título del libro.
     * @param preco Precio del libro.
     */
    public BookRequest(Long id, String titulo, Double preco) {
        this.id = id;
        this.titulo = titulo;
        this.preco = preco;
    }

    /*** Getters y Setters ***/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }


    public Double getPreco() {
        return preco;
    }

}
