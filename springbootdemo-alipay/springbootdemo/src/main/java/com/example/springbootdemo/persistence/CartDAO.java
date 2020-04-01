package com.example.springbootdemo.persistence;



import com.example.springbootdemo.domain.CartItem;

import java.util.List;

public interface CartDAO {
    void insertIntoCart(CartItem cartItem);
    List<CartItem> getCartItem(String username);
    void removeCartItem(String itemId);
    void updateCart(String itemId,int quantity);
    String selectItem(String username,String itemId);
    void increaseItemQuantity(String itemId);
}

