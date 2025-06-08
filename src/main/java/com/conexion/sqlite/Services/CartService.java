package com.conexion.sqlite.Services;

import com.conexion.sqlite.Domain.CartItem;

import java.util.List;

public interface CartService {

    public List<CartItem> getCartItems();
    public void postCartItem(int id);
    //public CartItem putCartItem(CartItem cartItem);
    public void deleteCartItem(int id);

}
