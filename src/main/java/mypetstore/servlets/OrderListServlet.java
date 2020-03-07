package mypetstore.servlets;

import mypetstore.domain.Order;
import mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class OrderListServlet extends HttpServlet {
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/ListOrders.jsp";
    private OrderService orderService = new OrderService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        List<Order> orderList = orderService.getOrdersByUsername(username);
        for(int i=0;i<orderList.size();i++){
            System.out.println(i+orderList.get(i).getUsername());
        }
        session.setAttribute("orderList",orderList);
        request.getRequestDispatcher(VIEW_ORDER).forward(request,response);
    }
}
