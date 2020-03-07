package mypetstore.servlets;

import mypetstore.domain.Cart;
import mypetstore.domain.CartItem;
import mypetstore.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewCartServlet extends HttpServlet {
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
    private static final String SIGN_ON = "/WEB-INF/jsp/account/SignonForm.jsp";
    private CartService cartService ;
    static int search_times=0;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        cartService = new CartService();
        if(session.getAttribute("account") != null) {
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }
            if(search_times==0) {
                List<CartItem> cartItems = cartService.getCartItem((String) session.getAttribute("username"));
                System.out.println("view"+cartItems.size());
                for (int i = 0; i < cartItems.size(); i++) {
                    cartItems.get(i).getItem().setProductId(cartItems.get(i).getItem().getProduct().getProductId());
                    cart.addItem(cartItems.get(i).getItem(), cartItems.get(i).isInStock());
                    cartItems.get(i).setQuantity(cart.getCartItemList().get(i).getItem().getQuantity());
                    System.out.println("fsa"+cart.getCartItemList().get(i).getItem().getQuantity());
                    System.out.println("why"+cart.getCartItemList().get(i).getQuantity());
                }
                search_times++;
            }
            request.getRequestDispatcher(VIEW_CART).forward(request, response);
        }
        else{
            String message_from_cart = "Please sign on first";
            session.setAttribute("message_from_cart",message_from_cart);
            request.getRequestDispatcher(SIGN_ON).forward(request,response);
        }
    }
}
