package mypetstore.servlets;

import mypetstore.domain.Cart;
import mypetstore.domain.LineItem;
import mypetstore.domain.Order;
import mypetstore.service.CartService;
import mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FinalOrderServlet extends HttpServlet {
    private static final String FINAL_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";
    private OrderService orderService = new OrderService();
    private CartService cartService = new CartService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        System.out.println(((Order)session.getAttribute("order")).getOrderDate());
        orderService.insertOrder((Order)session.getAttribute("order"));
        Cart cart = (Cart)session.getAttribute("cart");
        List<LineItem>lineItems = new ArrayList<LineItem>();
        lineItems = ((Order)request.getSession().getAttribute("order")).getLineItems();
        for(int i=0;i<lineItems.size();i++){
            System.out.println("remove"+lineItems.get(i).getItemId());
            cart.removeItemById(lineItems.get(i).getItemId());
            cartService.RemoveCart(lineItems.get(i).getItemId());
        }
        request.getRequestDispatcher(FINAL_ORDER).forward(request,response);
    }
}

