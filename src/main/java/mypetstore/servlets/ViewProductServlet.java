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
import java.util.ArrayList;
import java.util.List;

public class ViewProductServlet extends HttpServlet {
    private static final String VIEW_PRODUCT="/WEB-INF/jsp/catalog/Product.jsp";
    private String productId;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        productId = request.getParameter("productId");
        CategoryService categoryService = new CategoryService();
        Product product = categoryService.getProduct(productId);
        List<Item> itemList = categoryService.getItemListByProduct(productId);

        HttpSession session = request.getSession();
        session.setAttribute("product",product);
        session.setAttribute("itemList",itemList);
        request.getRequestDispatcher(VIEW_PRODUCT).forward(request,response);
    }
}
