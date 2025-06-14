package com.conexion.sqlite.Services;

import com.conexion.sqlite.Domain.CartItem;
import com.conexion.sqlite.Domain.Carts;
import com.conexion.sqlite.Domain.Customer;
import com.conexion.sqlite.Domain.Products;
import com.conexion.sqlite.Repository.CartsDBA;
import com.conexion.sqlite.Repository.CustomerDBA;
import com.conexion.sqlite.Repository.ProductsDBA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServicesImpl implements CartService {

    @Autowired
    public ProductsDBA productsDBA;
    @Autowired
    public CartsDBA cartsDBA;

    private List<CartItem> cart = new ArrayList<>();
    @Autowired
    private CustomerDBA customerDBA;

    @Override
    public List<CartItem> getCartItems() {
        List<Carts> carts = cartsDBA.findAll();
        for (Carts c : carts) {
            Products product = productsDBA.findById(c.getProduct_id())
                    .orElseThrow(() -> new RuntimeException("No existe el producto con el id: " + c.getProduct_id()));
            Customer customer = customerDBA.findById(c.getCustomer_id())
                    .orElseThrow(() -> new RuntimeException("No existe el producto con el id: " + c.getCustomer_id()));
            CartItem cartItem = new CartItem
                    (c.getCart_id(), c.getAmount()*product.getPrice(), c.getAmount() ,product, customer);
            cart.add(cartItem);
        }
        return cart;
    }

    @Override
    public Carts postCartItem(Carts carts) {
        Carts newCarts = new Carts();
        List<Carts> cartsItems = cartsDBA.findAll();
        for (Carts c : cartsItems) {
            if (c.getProduct_id().equals(carts.getProduct_id())) {
                Products products = productsDBA.findById(carts.getProduct_id())
                        .orElseThrow(() -> new RuntimeException("No existe el producto con el id: " + carts.getProduct_id()));
                newCarts.setCart_price(c.getCart_price() + products.getPrice());
                newCarts.setAmount(c.getAmount() + 1);
                return cartsDBA.save(newCarts);
            }
        }
        newCarts.setCustomer_id(carts.getCustomer_id());
        newCarts.setProduct_id(carts.getProduct_id());
        newCarts.setAmount(carts.getAmount());
        Products products = productsDBA.findById(carts.getProduct_id())
                .orElseThrow(() -> new RuntimeException("No existe el producto con el id: " + carts.getProduct_id()));
        newCarts.setCart_price(carts.getAmount()*products.getPrice());
        return cartsDBA.save(newCarts);
    }


    @Override
    public void deleteCartItem(Integer id) {
        List<Carts> cartsItems = cartsDBA.findAll();
        for (Carts c : cartsItems) {
            if (c.getProduct_id().equals(id)) {
                Carts newCarts = c;
                newCarts.setAmount(c.getAmount() - 1);
                cartsDBA.save(newCarts);
                return;
            }
        }
        cartsDBA.deleteById(id);
    }
}
