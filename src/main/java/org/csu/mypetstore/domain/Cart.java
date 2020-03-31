package org.csu.mypetstore.domain;

import java.math.BigDecimal;
import java.util.*;

public class Cart {
    private final Map<String, CartItem> itemMap = Collections.synchronizedMap(new HashMap<String, CartItem>());

    //    private final List<CartItem> itemList = new ArrayList<CartItem>();
    private List<CartItem> cartItems = new ArrayList<CartItem>();

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
//    public Iterator<CartItem> getCartItems() {
//        return itemList.iterator();
//    }
//
//    public List<CartItem> getCartItemList() {
//        return itemList;
//    }

//    public void setCartItemList(List<CartItem> itemList) {
//         this.itemList=itemList;
//    }
//
//    public int getNumberOfItems() {
//        return itemList.size();
//    }
//
//    public Iterator<CartItem> getAllCartItems() {
//        return itemList.iterator();
//    }

    public CartItem containsItemId(String itemId) {
        List<String> itemIdList = new ArrayList<String>();
        CartItem cartItem = new CartItem();
        for(int i=0;i<cartItems.size();i++) {
            if(cartItems.get(i).getItemId().equals(itemId)){
                cartItem = cartItems.get(i);
                return cartItem;
            }
        }
        return null;
    }

//    public void addItem(Item item, boolean isInStock) {
//        CartItem cartItem = (CartItem) itemMap.get(item.getItemId());
//        if (cartItem == null) {
//            cartItem = new CartItem();
////            cartItem.setItem(item);
//            cartItem.setQuantity(0);
////            cartItem.setInStock(isInStock);
//            itemMap.put(item.getItemId(), cartItem);
////            itemList.add(cartItem);
//        }
////        cartItem.incrementQuantity();
//    }

//    public Item removeItemById(String itemId) {
//        CartItem cartItem = (CartItem) itemMap.remove(itemId);
//        if (cartItem == null) {
//            return null;
//        } else {
////            itemList.remove(cartItem);
//            return null;
////            return cartItem.getItem();
//        }
//    }
    public void removeCartItem(String itemId) {
        for(int i=0;i<cartItems.size();i++) {
            if(cartItems.get(i).getItemId().equals(itemId)){
                 cartItems.remove(i);
            }
        }
    }

//    public void incrementQuantityByItemId(String itemId) {
//        CartItem cartItem = (CartItem) itemMap.get(itemId);
////        cartItem.incrementQuantity();
//    }

    public void setQuantityByItemId(String itemId, int quantity) {
        CartItem cartItem = (CartItem) itemMap.get(itemId);
        cartItem.setQuantity(quantity);
    }

    public BigDecimal getSubTotal() {
        BigDecimal subTotal = new BigDecimal("0");
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem cartItem = cartItems.get(i);
            Item item = cartItem.getItem();
            BigDecimal listPrice = item.getListPrice();
            BigDecimal quantity = new BigDecimal(String.valueOf(cartItem.getQuantity()));
            subTotal = subTotal.add(listPrice.multiply(quantity));
        }
        return subTotal;
    }
}
