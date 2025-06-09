package com.conexion.sqlite.Services;

import com.conexion.sqlite.Domain.CartItem;
import com.conexion.sqlite.Domain.Products;
import com.conexion.sqlite.Repository.ProductsDBA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServicesImpl implements CartService {

    @Autowired
    public ProductsDBA productsDBA;

    private List<CartItem> cart = new ArrayList<>();

    @Override
    public List<CartItem> getCartItems() {
        return cart;
    }

    @Override
    public void postCartItem(int id) {
        Products productsNew = productsDBA.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el producto con el id: " + id));
        for (CartItem cartItem : cart) {
            if (cartItem.getProduct().getId() == productsNew.getId()) {
                cartItem.setCantidad(cartItem.getCantidad() + 1);
                return;
            }
        }
        cart.add(new CartItem(productsNew, 1));
    }

//    @Override
//    public CartItem putCartItem(CartItem cartItem) {
//        return null;
//    }

    @Override
    public void deleteCartItem(int id) {
        for (CartItem cartItem : cart) {
            if (cartItem.getProduct().getId() == id) {
                if (cartItem.getCantidad() > 1) {
                    cartItem.setCantidad(cartItem.getCantidad() - 1);
                    return;
                }else {
                    cart.remove(cartItem);
                    return;
                }
            }
        }
    }
}
