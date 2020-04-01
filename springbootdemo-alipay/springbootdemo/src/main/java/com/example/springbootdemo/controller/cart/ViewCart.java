package com.example.springbootdemo.controller.cart;

import com.example.springbootdemo.domain.Cart;
import com.example.springbootdemo.domain.CartItem;
import com.example.springbootdemo.domain.Item;
import com.example.springbootdemo.service.CartService;
import com.example.springbootdemo.service.CatalogService;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/cart")
@SessionAttributes(value = {"cart"})
public class ViewCart {
//    private int times = 0;
    @Autowired
    private CartService cartService;
    @Autowired
    private CatalogService categoryService;
    @GetMapping("/view")
    //查看购物车
    public String getCart(Model model, HttpSession session){
        String username = (String)session.getAttribute("username");

        List<CartItem> cartItems= cartService.getCartItem(username);
        if(username != null){
            Cart cart = new Cart();
            for (int i = 0; i < cartItems.size(); i++) {
                //库存判断
                if(cartItems.get(i).getIsStock() > 0) {
                    cartItems.get(i).setInStock(true);
                    cartItems.get(i).getItem().setProductId(cartItems.get(i).getItem().getProduct().getProductId());
                    cart.addItem(cartItems.get(i).getItem(), cartItems.get(i).isInStock());
                    cartItems.get(i).setQuantity(cart.getCartItemList().get(i).getItem().getQuantity());
                }
                else {
                    cartItems.get(i).setInStock(false);
                }
//                cartItems.get(i).getItem().setProductId(cartItems.get(i).getItem().getProduct().getProductId());
//                cart.addItem(cartItems.get(i).getItem(), cartItems.get(i).isInStock());
//                cartItems.get(i).setQuantity(cart.getCartItemList().get(i).getItem().getQuantity());
            }
            model.addAttribute("cart", cart);

            return "cart/Cart";
        }
        else {
            return "catalog/Main";
        }
    }
    @GetMapping("/insert")
    public String InsertToCart(String workingItemId, Model model, HttpSession session){
        String username = (String) session.getAttribute("username");
        Cart cart = (Cart)session.getAttribute("cart");
        String loseMes = null;
        if(cart == null){
            cart = new Cart();
        }

        if(cart.containsItemId(workingItemId)){
            cart.incrementQuantityByItemId_1(workingItemId);
            cartService.IncreaseQuantity(workingItemId);
        }
        else{
            boolean isInStack = categoryService.isItemInStock(workingItemId);
            if (isInStack){
                Item item = categoryService.getItem(workingItemId);
                cart.addItem(item,isInStack);
                CartItem cartItem = cart.getCartItemList().get(cart.getNumberOfItems()-1);
                cartItem.getItem().setProductId(cart.getCartItemList().get(cart.getNumberOfItems()-1).getItem().getProduct().getProductId());
                cartItem.setQuantity(1);
                cartItem.setUsername((String)session.getAttribute("username"));
                cartService.insertCart(cartItem);
            }
            else {
                loseMes = "No enough goods";
            }
//            Item item = categoryService.getItem(workingItemId);
//            cart.addItem(item,isInStack);
//            CartItem cartItem = cart.getCartItemList().get(cart.getNumberOfItems()-1);
//            cartItem.getItem().setProductId(cart.getCartItemList().get(cart.getNumberOfItems()-1).getItem().getProduct().getProductId());
//            cartItem.setQuantity(1);
//            cartItem.setUsername((String)session.getAttribute("username"));
//            cartService.insertCart(cartItem);

        }
        model.addAttribute("cart",cart);
        model.addAttribute("losMes", loseMes);
        return "cart/Cart";
    }
    @GetMapping("/delete")
    public String DeleteFromCart(String workingItemId, Model model, HttpSession session){

        Cart cart = (Cart) session.getAttribute("cart");
        Item item = cart.removeItemById(workingItemId);
//        cartService = new CartService();
        cartService.RemoveCart(workingItemId);
        if(item == null){
//            session.setAttribute("message","no goods");
//            request.getRequestDispatcher(ERROR);
            return "main";
        }
        else {
//            String username_diary = (String) session.getAttribute("username");
//            if(username_diary!=null) {
//                Date currentTime = new Date();
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String time_diary = formatter.format(currentTime);
//                String operation_diary = "remove from cart";
//                Diary diary = new Diary();
//                diary.setUsername(username_diary);
//                diary.setDate(time_diary);
//                diary.setOperation(operation_diary);
//                diaryService.insertDiary(diary);
//            }
//            request.getRequestDispatcher(REMOVE_ITEM).forward(request,response);
            return "cart/Cart";
        }
    }
    @PostMapping("/update")
    public String updateCart(String itemId, HttpSession session, Model model) {
        System.out.println(itemId);
        String loseMes = null;
        String [] params = itemId.split(",");
        for(int i = 0; i<params.length; i++){
            System.out.println(params[i]);
        }

        Cart cart = (Cart) session.getAttribute("cart");
        int num_item = cart.getNumberOfItems();
        List<CartItem> cartList = cart.getCartItemList();
        for (int i = 0; i < num_item; i++) {
            int number = cartList.get(i).getQuantity();
            int number_fix = new Integer(params[i]);
            if (number != number_fix) {
                System.out.println(number_fix);
                if(categoryService.getQuantity(cartList.get(i).getItem().getItemId()) < number_fix){
                    loseMes = "No enough goods";
                }
                else {
                    cartService.UpdateCart(cartList.get(i).getItem().getItemId(), number_fix);
                    cart.setQuantityByItemId(cartList.get(i).getItem().getItemId(), number_fix);
                    break;
                }
            }
        }
        model.addAttribute("losMes", loseMes);
        return "cart/Cart";
    }
}
