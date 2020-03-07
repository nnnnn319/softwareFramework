package mypetstore.servlets;

import mypetstore.domain.Cart;
import mypetstore.domain.CartItem;
import mypetstore.domain.Item;
import mypetstore.service.CartService;
import mypetstore.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


//还没做判断数量是否够
public class UpdateCartServlet extends HttpServlet {
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
    CategoryService categoryService = new CategoryService();
    CartService cartService = new CartService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        if(cart == null){
//            System.out.println("null");
        }
        else {
            int num_item = cart.getNumberOfItems();
//            System.out.println("itemNumber "+num_item);
            List<CartItem> cartList = cart.getCartItemList();
            for (int i = 0; i < num_item; i++) {
                int number = cartList.get(i).getQuantity();
//                System.out.println("数量"+number);
                int number_fix = Integer.valueOf(request.getParameter(cartList.get(i).getItem().getItemId()));
                if (number != number_fix) {
                    System.out.println(number_fix);
                    cartService.UpdateCart(cartList.get(i).getItem().getItemId(),number_fix);
                    cart.setQuantityByItemId(cartList.get(i).getItem().getItemId(), number_fix);
                    break;
                }
            }
            session.setAttribute("cart", cart);

            request.getRequestDispatcher(VIEW_CART).forward(request, response);
        }
    }
}
