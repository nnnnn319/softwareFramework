package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.persistence.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartMapper cartMapper;

    public List<CartItem> getCartItemByUsername(String username) {
        return cartMapper.getCartItemListByCartId(username);
    }

    @Transactional
    public void insertCartItem(CartItem cartItem) {
        cartMapper.insertCartItem(cartItem);

    }

    @Transactional
    public void removeCartItem(CartItem cartItem) {
        cartMapper.removeCartItem(cartItem);
    }

    @Transactional
    public void updateCartItem(CartItem cartItem) {
        cartMapper.updateCartItem(cartItem);
    }




}
