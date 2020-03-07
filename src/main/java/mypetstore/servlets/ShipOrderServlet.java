package mypetstore.servlets;

import mypetstore.domain.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShipOrderServlet extends HttpServlet {
    private static final String SHIP = "/WEB-INF/jsp/order/ViewOrder.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        order.setShipToFirstName(request.getParameter("order.shipToFirstName"));
        order.setShipToLastName(request.getParameter("order.shipToLastName"));
        order.setShipAddress1(request.getParameter("order.shipAddress1"));
        order.setShipAddress2(request.getParameter("order.shipAddress2"));
        order.setShipCity(request.getParameter("order.shipCity"));
        order.setShipState(request.getParameter("order.shipState"));
        order.setShipZip(request.getParameter("order.shipZip"));
        order.setShipCountry(request.getParameter("order.shipCountry"));
        session.setAttribute("order",order);
        request.getRequestDispatcher(SHIP).forward(request,response);
    }
}
