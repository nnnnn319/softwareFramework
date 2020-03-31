package com.example.springbootdemo.service;

import com.example.springbootdemo.domain.CartItem;

import java.util.List;

public interface CartService  {
    public void insertCart(CartItem cartItem);
    public List<CartItem> getCartItem(String username);
    public void RemoveCart(String itemId);
    public void UpdateCart(String itemId,int quantity);
    public void IncreaseQuantity(String itemId);

}
