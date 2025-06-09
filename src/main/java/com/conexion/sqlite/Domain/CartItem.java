package com.conexion.sqlite.Domain;

public class CartItem {

    private Products products;
    private int cantidad;

    public CartItem(Products products, int cantidad) {
        this.products = products;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public Products getProduct() {
        return products;
    }

    public void setProduct(Products products) {
        this.products = products;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return products.getPrice() * cantidad;
    }

}
