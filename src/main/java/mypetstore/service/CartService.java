package mypetstore.service;

import mypetstore.domain.CartItem;
import mypetstore.persistence.CartDAO;
import mypetstore.persistence.impl.CartDAOImpl;

import java.util.List;

public class CartService {
    private CartDAO cartDAO;
    public CartService(){
        cartDAO = new CartDAOImpl();
    }
    public void insertCart(CartItem cartItem){
        cartDAO.insertIntoCart(cartItem);
    }
    public List<CartItem> getCartItem(String username){
        return cartDAO.getCartItem(username);
    }
    public void RemoveCart(String itemId){
        cartDAO.removeCartItem(itemId);
    }
    public void UpdateCart(String itemId,int quantity){
        cartDAO.updateCart(itemId,quantity);
    }
    public void IncreaseQuantity(String itemId){
        cartDAO.increaseItemQuantity(itemId);
    }
}
