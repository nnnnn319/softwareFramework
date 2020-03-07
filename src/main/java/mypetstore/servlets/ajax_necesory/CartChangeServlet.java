package mypetstore.servlets.ajax_necesory;

import com.alibaba.fastjson.JSON;
import mypetstore.domain.Cart;
import mypetstore.domain.Item;
import mypetstore.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class CartChangeServlet extends HttpServlet {
    private CartService cartService = new CartService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("cart");
        String itemId = request.getParameter("itemId").substring(17);
        String changeNum = request.getParameter("change");
        System.out.println(itemId+"  "+changeNum);
        cartService.UpdateCart(itemId,Integer.valueOf(changeNum));
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.setQuantityByItemId(itemId, Integer.valueOf(changeNum));
        String sub = cart.getSubTotal().toString();
        if(changeNum != "") {
            response.getWriter().print(sub);
        }
    }
}
