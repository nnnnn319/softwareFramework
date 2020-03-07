package mypetstore.servlets;

import mypetstore.domain.Category;
import mypetstore.domain.Product;
import mypetstore.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewCategoryServlet extends HttpServlet {
    private String categoryId;
    private static final String VIEW_CATEGORY="/WEB-INF/jsp/catalog/Category.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        categoryId = request.getParameter("categoryId");
        CategoryService categoryService = new CategoryService();
        Category category = categoryService.getCategory(categoryId);
        List<Product> productList = categoryService.getProductListByCategory(categoryId);

        HttpSession session = request.getSession();
        session.setAttribute("category",category);
        session.setAttribute("productList",productList);
        request.getRequestDispatcher(VIEW_CATEGORY).forward(request,response);
    }
}
