package com.conexion.sqlite.Domain;

public class CartItem {

    private Product product;
    private int cantidad;

    public CartItem(Product product, int cantidad) {
        this.product = product;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return product.getPrice() * cantidad;
    }

}
