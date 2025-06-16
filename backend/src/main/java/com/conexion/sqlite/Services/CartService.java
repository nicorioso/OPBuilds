package com.conexion.sqlite.Services;

import com.conexion.sqlite.Domain.CartItem;
import com.conexion.sqlite.Domain.Carts;

import java.util.List;

public interface CartService {

    public List<CartItem> getCartItems();
    public Carts postCartItem(Carts id);
    //public Carts putCartItem(Carts cartItem);
    public void deleteCartItem(Integer id);

}
