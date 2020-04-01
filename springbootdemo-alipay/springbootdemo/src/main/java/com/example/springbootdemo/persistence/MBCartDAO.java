package com.example.springbootdemo.persistence;

import com.example.springbootdemo.domain.CartItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MBCartDAO {
    void insertIntoCart(CartItem cartItem);
    List<CartItem> getCartItem(String username);
    void removeCartItem(String itemId);
    void updateCart(String itemId,int quantity);
    String selectItem(String username,String itemId);
    void increaseItemQuantity(String itemId);
}
