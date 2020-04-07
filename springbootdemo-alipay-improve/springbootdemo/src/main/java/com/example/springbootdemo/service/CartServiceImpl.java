package com.example.springbootdemo.service;

import com.example.springbootdemo.domain.CartItem;
import com.example.springbootdemo.persistence.CartDAO;
import com.example.springbootdemo.persistence.CartDAOImpl;
import com.example.springbootdemo.persistence.MBCartDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private MBCartDAO mbCartDAO;
    @Override
    public void insertCart(CartItem cartItem){
        mbCartDAO.insertIntoCart(cartItem);
    }
    @Override
    public List<CartItem> getCartItem(String username){
        return mbCartDAO.getCartItem(username);
    }
    @Override
    public void RemoveCart(String itemId){
        mbCartDAO.removeCartItem(itemId);
    }
    @Override
    public void UpdateCart(String itemId,int quantity){
        mbCartDAO.updateCart(itemId,quantity);
    }
    @Override
    public void IncreaseQuantity(String itemId){
        mbCartDAO.increaseItemQuantity(itemId);
    }

}
