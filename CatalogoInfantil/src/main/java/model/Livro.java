package model;

import java.time.LocalDate;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private String genero;
    private LocalDate dataPublicacao; // Alterado para LocalDate
    private String editora;
    private String sinopse;

    // Construtor sem ID (para inserções, onde o ID é gerado automaticamente pelo banco de dados)
    public Livro(String titulo, String autor, String genero, LocalDate dataPublicacao, String editora, String sinopse) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.dataPublicacao = dataPublicacao;
        this.editora = editora;
        this.sinopse = sinopse;
    }

    // Construtor com ID (para buscas e atualizações, onde já temos um ID definido)
    public Livro(int id, String titulo, String autor, String genero, LocalDate dataPublicacao, String editora, String sinopse) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.dataPublicacao = dataPublicacao;
        this.editora = editora;
        this.sinopse = sinopse;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }
}
