package mypetstore.servlets;

import mypetstore.domain.Order;
import mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HistoryOrderServlet extends HttpServlet {
    private static final String FINAL_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";
    private OrderService orderService = new OrderService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        System.out.println(orderId);
        Order order = orderService.getOrder(Integer.valueOf(orderId));
        request.getSession().setAttribute("order",order);
        request.getRequestDispatcher(FINAL_ORDER).forward(request,response);
    }
}
