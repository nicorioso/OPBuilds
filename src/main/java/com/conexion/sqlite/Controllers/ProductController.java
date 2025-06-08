package com.conexion.sqlite.Controllers;

import com.conexion.sqlite.Domain.Product;
import com.conexion.sqlite.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        Optional<Product> productOpt = productService.getProduct(id);

        return productOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product newProduct = productService.addProduct(product);
        URI location = URI.create("/Products/" + newProduct.getId());
        return ResponseEntity.created(location).body(newProduct);
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        try {
            Product update = productService.updateProduct(product.getId(), product);
            return ResponseEntity.ok(update);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PatchMapping
    public ResponseEntity<?> patchProduct(@RequestBody Product product) {
        try {
            Product patch = productService.patchProduct(product.getId(), product);
            return ResponseEntity.ok(patch);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el producto con el id " + product.getId());
        }
    }

}
