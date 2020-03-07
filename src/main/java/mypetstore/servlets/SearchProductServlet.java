package mypetstore.servlets;

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

public class SearchProductServlet extends HttpServlet {
    private static final String SEARCH = "/WEB-INF/jsp/catalog/SearchProducts.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keywords = request.getParameter("keyword");
        if(keywords!=null) {
            CategoryService categoryService = new CategoryService();
            List<Product> productList = categoryService.searchProductList(keywords);
            HttpSession session = request.getSession();
            session.setAttribute("productList", productList);
            request.getRequestDispatcher(SEARCH).forward(request, response);
        }
        else
            System.out.println("null");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
