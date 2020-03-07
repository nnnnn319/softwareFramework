package mypetstore.servlets;

import mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewOrderFormServlet extends HttpServlet {
    private static final String CHECK_1 = "/WEB-INF/jsp/order/NewOrderForm.jsp";
    OrderService orderService = new OrderService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> creditCard_types = new ArrayList<String>();
        creditCard_types.add("Visa");
        creditCard_types.add("MasterCard");
        creditCard_types.add("American Express");
        HttpSession session = request.getSession();
        session.setAttribute("credit",creditCard_types);
        request.getRequestDispatcher(CHECK_1).forward(request,response);
    }
}
