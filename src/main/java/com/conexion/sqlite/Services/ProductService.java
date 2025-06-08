package com.conexion.sqlite.Services;

import com.conexion.sqlite.Domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public List<Product> getProducts();
    public Optional<Product> getProduct(int id);
    public Product addProduct(Product product);
    public Product updateProduct(int id, Product product);
    public void deleteProduct(int id);
    public Product patchProduct(int id, Product product);

}
