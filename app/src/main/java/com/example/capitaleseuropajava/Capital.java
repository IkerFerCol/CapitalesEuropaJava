package com.example.capitaleseuropajava;

import java.io.Serializable;

public class Capital implements Serializable {
    private int id;
    private String nombre;
    private String img;
    private Integer poblacion;

    
    public Capital(int id, String nombre, String img, Integer poblacion) {
        this.id = id;
        this.nombre = nombre;
        this.img = img;
        this.poblacion = poblacion;
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

    public Integer getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(Integer poblacion) {
        this.poblacion = poblacion;
    }


    @Override
    public String toString() {
        return "Capital{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", img='" + img + '\'' +
                ", poblacion=" + poblacion +
                '}';
    }


}
