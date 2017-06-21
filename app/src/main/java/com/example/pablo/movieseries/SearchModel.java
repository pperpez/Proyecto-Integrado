package com.example.pablo.movieseries;

import ir.mirrajabi.searchdialog.core.Searchable;

public class SearchModel implements Searchable {

    private Integer idPelicula;
    private String titulo;
    private String imagen;

    public SearchModel(Integer idPelicula, String titulo, String imagen) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.imagen = imagen;
    }

    public Integer getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Integer idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String getTitle() {
        return titulo;
    }
}
