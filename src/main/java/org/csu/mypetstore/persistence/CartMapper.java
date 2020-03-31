package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.CartItem;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartMapper {
    List<CartItem>  getCartItemListByCartId(String cartId);

    CartItem getCartItemByCardIdAndItemId(String cartId,String itemId);

    void insertCartItem(CartItem cartItem);

    void updateCartItem(CartItem cartItem);

    void removeCartItem(CartItem cartItem);
}
