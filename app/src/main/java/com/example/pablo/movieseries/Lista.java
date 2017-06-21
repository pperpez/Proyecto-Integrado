package com.example.pablo.movieseries;

public class Lista {

    private Integer idLista;
    private String nombre;

    public Lista(Integer idLista, String nombre) {
        this.idLista = idLista;
        this.nombre = nombre;
    }

    public Integer getIdLista() {
        return idLista;
    }

    public void setIdLista(Integer idLista) {
        this.idLista = idLista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
