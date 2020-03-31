package org.csu.mypetstore.controller;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
@SessionAttributes({"cart"})
public class CartController {
    @Autowired
    private CatalogService catalogService;
    @Autowired
    CartService cartService;

    @GetMapping("/viewCart")
    public String viewCart(Model model, HttpSession session) {
        Account account = (Account)session.getAttribute("account");
        if (account != null) {
            return "cart/cart";
        }
        String message ="Please login first";
        model.addAttribute("message",message);
        return "account/signOnForm";
    }

    //添加商品进入购物车
    @GetMapping("/addItemToCart")
    public String addItemToCart(Model model, String itemId,HttpSession session) {
        String username =(String)session.getAttribute("username");
        Account account =(Account)session.getAttribute("account");
        if(account !=null) {
            Cart cart = (Cart) session.getAttribute("cart");
            CartItem cartItem = cart.containsItemId(itemId);
            if (cartItem != null) {
                boolean inStock = catalogService.isItemInStock(itemId,1);
                if(inStock) {
                    //减少库存
                    cartItem.incrementQuantity();
                    CartItem cartItem2 =new CartItem();
                    cartItem2.setItemId(itemId);
                    cartItem2.setQuantity(-1);
                    catalogService.updateInventoryQuantity(cartItem2);
                    //加入购物车
                    cartItem.setInStock(inStock);
                    cartService.updateCartItem(cartItem);
                }
                else {
                    String message = "Sorry add failed,insufficient inventory";
                    model.addAttribute("message",message);
                }
            } else {
                CartItem cartItem1 = new CartItem();
                cartItem1.setQuantity(1);
                boolean inStock = catalogService.isItemInStock(itemId,1);
                if(inStock) {
                    Item item = catalogService.getItem(itemId);
                    cartItem1.setItemId(itemId);
                    CartItem cartItem2 =new CartItem();
                    cartItem2.setItemId(itemId);
                    cartItem2.setQuantity(-1);
                    //减少库存
                    catalogService.updateInventoryQuantity(cartItem2);
                    cartItem1.setCartId(username);
                    cartItem1.setItem(item);
                    cartItem1.setInStock(inStock);
                    cart.getCartItems().add(cartItem1);
                    cartService.insertCartItem(cartItem1);
                }
                else {
                    String message = "Sorry add failed,insufficient inventory";
                    model.addAttribute("message",message);
                }
            }
            model.addAttribute("cart", cart);
            return "cart/cart";
        }
        String message ="Please login first";
        model.addAttribute("message",message);
        return "account/signOnForm";
    }


    @GetMapping("/removeItemFromCart")
    public String removeItemFromCart(String itemId, Model model,HttpSession session){
        Cart cart = (Cart)session.getAttribute("cart");
        String username =(String)session.getAttribute("username");
        CartItem cartItem =new CartItem();
        cartItem.setCartId(username);
        cartItem.setItemId(itemId);
        cartService.removeCartItem(cartItem);

        //增加库存
        CartItem cartItem1 = cart.containsItemId(itemId);
        catalogService.updateInventoryQuantity(cartItem1);

        cart.removeCartItem(itemId);
        model.addAttribute("cart",cart);

        return "cart/cart";
    }

}
