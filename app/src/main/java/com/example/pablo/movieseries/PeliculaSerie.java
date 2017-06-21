package com.example.pablo.movieseries;

public class PeliculaSerie {

    private Integer idPelicula;
    private String titulo;
    private Integer duracionMin;
    private Integer anyoLanzamiento;
    private String sinopsis;
    private String imagen;
    private String urlTrailer;

    public PeliculaSerie(Integer idPelicula, String titulo, Integer duracionMin, Integer anyoLanzamiento, String sinopsis, String imagen, String urlTrailer) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.duracionMin = duracionMin;
        this.anyoLanzamiento = anyoLanzamiento;
        this.sinopsis = sinopsis;
        this.imagen = imagen;
        this.urlTrailer = urlTrailer;
    }

    public PeliculaSerie(Integer idPelicula, String titulo, String imagen) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.imagen = imagen;
    }


    public PeliculaSerie() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getDuracionMin() {
        return duracionMin;
    }

    public void setDuracionMin(Integer duracionMin) {
        this.duracionMin = duracionMin;
    }

    public Integer getAnyoLanzamiento() {
        return anyoLanzamiento;
    }

    public void setAnyoLanzamiento(Integer anyoLanzamiento) {
        this.anyoLanzamiento = anyoLanzamiento;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getUrlTrailer() {
        return urlTrailer;
    }

    public void setUrlTrailer(String urlTrailer) {
        this.urlTrailer = urlTrailer;
    }

    public Integer getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Integer idPelicula) {
        this.idPelicula = idPelicula;
    }
}
