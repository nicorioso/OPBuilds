package com.conexion.sqlite.Controllers;

import com.conexion.sqlite.Domain.CartItem;
import com.conexion.sqlite.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartItem>> getCartService() {
        return ResponseEntity.ok(cartService.getCartItems());
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> postCartService(@PathVariable int id) {
        try {
            cartService.postCartItem(id);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el producto con el id " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCartService(@PathVariable int id) {
        cartService.deleteCartItem(id);
        return ResponseEntity.ok().build();
    }

}