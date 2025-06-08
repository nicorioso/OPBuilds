package com.conexion.sqlite.Services;

import com.conexion.sqlite.Domain.Product;
import com.conexion.sqlite.Repository.ProductsDBA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductsDBA productsDBA;

    @Override
    public List<Product> getProducts() {
        return productsDBA.findAll();
    }

    @Override
    public Optional<Product> getProduct(int id) {
        Optional<Product> product = productsDBA.findById(id);
        if (product.isPresent()) {
            return product;
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public Product addProduct(Product product) {
        return productsDBA.save(product);
    }

    @Override
    public Product updateProduct(int id, Product productNew) {
        Product productExist = productsDBA.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el producto con el id: " + id));
        productExist.setName(productNew.getName());
        productExist.setDescription(productNew.getDescription());
        productExist.setCategory(productNew.getCategory());
        productExist.setPrice(productNew.getPrice());
        return productsDBA.save(productExist);
    }

    @Override
    public void deleteProduct(int id) {
        Product product = productsDBA.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el producto con el id: " + id));
        productsDBA.delete(product);
    }

    @Override
    public Product patchProduct(int id, Product product) {
        Product productExist = productsDBA.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el producto con el id: " + id));
        if (product.getName() != null) {
            productExist.setName(product.getName());
        }
        if (product.getDescription() != null) {
            productExist.setDescription(product.getDescription());
        }
        if (product.getCategory() != null) {
            productExist.setCategory(product.getCategory());
        }
        if (product.getPrice() != null) {
            productExist.setPrice(product.getPrice());
        }
        return productsDBA.save(productExist);
    }

}
