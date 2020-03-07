package mypetstore.servlets;

import mypetstore.domain.Item;
import mypetstore.domain.Product;
import mypetstore.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewItemServlet extends HttpServlet {
    private static final String VIEW_ITEM = "/WEB-INF/jsp/catalog/Item.jsp";
    String itemId;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        itemId=request.getParameter("itemId");
        CategoryService categoryService = new CategoryService();
        Item item = categoryService.getItem(itemId);
        Product product = item.getProduct();
        HttpSession session = request.getSession();
        session.setAttribute("item",item);
        session.setAttribute("product",product);
        request.getRequestDispatcher(VIEW_ITEM).forward(request,response);
    }
}
