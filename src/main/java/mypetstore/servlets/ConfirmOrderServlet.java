package mypetstore.servlets;

import mypetstore.domain.*;
import mypetstore.service.OrderService;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConfirmOrderServlet extends HttpServlet {
    private static final String CONFIRM = "/WEB-INF/jsp/order/ConfirmOrder.jsp";
    private static final String SHIP = "/WEB-INF/jsp/order/ShippingForm.jsp";
    private OrderService orderService = new OrderService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        Cart cart = (Cart)session.getAttribute("cart");

        Order order = new Order();
        order.initOrder(account,cart);
        order.setCreditCard(request.getParameter("order.creditCard"));
        order.setCardType(request.getParameter("card_type"));
        order.setExpiryDate(request.getParameter("order.expiryDate"));
        session.setAttribute("order",order);
        if(request.getParameter("shippingAddressRequired") == null) {
            request.getRequestDispatcher(CONFIRM).forward(request, response);
        }
        else {
            request.getRequestDispatcher(SHIP).forward(request,response);
        }
    }
}
