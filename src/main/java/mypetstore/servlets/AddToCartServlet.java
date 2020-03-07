package mypetstore.servlets;

import mypetstore.domain.*;
import mypetstore.service.CartService;
import mypetstore.service.CategoryService;
import mypetstore.service.DiaryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddToCartServlet extends HttpServlet {
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
    private String workingItemId;
    private Cart cart;
    private CategoryService categoryService;
    private CartService cartService;
    private DiaryService diaryService=new DiaryService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        workingItemId = request.getParameter("workingItemId");
        cartService = new CartService();
        HttpSession session = request.getSession();
        cart = (Cart)session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
        }

        if(cart.containsItemId(workingItemId)){
            cart.incrementQuantityByItemId_1(workingItemId);
            cartService.IncreaseQuantity(workingItemId);
        }
        else{
            categoryService = new CategoryService();
            boolean isInStack = categoryService.isItemInStock(workingItemId);
            Item item = categoryService.getItem(workingItemId);
            cart.addItem(item,isInStack);
            CartItem cartItem = cart.getCartItemList().get(cart.getNumberOfItems()-1);
            cartItem.getItem().setProductId(cart.getCartItemList().get(cart.getNumberOfItems()-1).getItem().getProduct().getProductId());
            cartItem.setQuantity(1);
            cartItem.setUsername((String)session.getAttribute("username"));
            cartService.insertCart(cartItem);
        }
        session.setAttribute("cart",cart);
//        cartService = new CartService();
//        CartItem cartItem = cart.getCartItemList().get(cart.getNumberOfItems()-1);
//        cartItem.getItem().setProductId(cart.getCartItemList().get(cart.getNumberOfItems()-1).getItem().getProduct().getProductId());
//        cartItem.setUsername((String)session.getAttribute("username"));
//        cartService.insertCart(cartItem);
        String username_diary = (String) session.getAttribute("username");
        if(username_diary!=null) {
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time_diary = formatter.format(currentTime);
            String operation_diary = "add to cart";
            Diary diary = new Diary();
            diary.setUsername(username_diary);
            diary.setDate(time_diary);
            diary.setOperation(operation_diary);
            diaryService.insertDiary(diary);
        }
        request.getRequestDispatcher(VIEW_CART).forward(request,response);
    }
}
