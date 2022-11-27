package com.example.evfinal1.model;

public class Producto {
    String  nombreProducto, descripcionProducto;

    public Producto(){}

    public Producto( String nombreProducto, String descripcionProducto) {

        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;



    }



    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }


}
