package com.example.mybookshelf;

public class Books {
    private String Titulo;
    private String Categoria;
    private String Opiniao;
    private int Thumbnail;

    public Books(String titulo, String categoria, String opiniao, int thumbnail) {
        Titulo = titulo;
        Categoria = categoria;
        Opiniao = opiniao;
        Thumbnail = thumbnail;
    }

    public Books(){

    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getOpiniao() {
        return Opiniao;
    }

    public void setOpiniao(String opiniao) {
        Opiniao = opiniao;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}

