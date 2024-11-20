package com.example.capitaleseuropajava;

public class Capital {
    int id;
    String nombre;
    String img;
    int poblacion;
    String pais;

    public Capital(int id, String nombre, String img, int poblacion, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.img = img;
        this.poblacion = poblacion;
        this.pais = pais;
    }

    public Capital() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Capital{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", img='" + img + '\'' +
                ", poblacion=" + poblacion +
                ", pais='" + pais + '\'' +
                '}';
    }
}
