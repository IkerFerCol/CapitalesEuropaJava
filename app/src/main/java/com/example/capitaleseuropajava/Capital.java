package com.example.capitaleseuropajava;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "CapitalsofEurope")
public class Capital implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nombre;
    private String img;
    private Integer poblacion;
    private String pais;

    
    public Capital(int id, String nombre, String img, Integer poblacion, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.img = img;
        this.poblacion = poblacion;
        this.pais = pais;
    }

    public Capital() {

    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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
                ", pais='" + pais + '\'' +
                '}';
    }
}
