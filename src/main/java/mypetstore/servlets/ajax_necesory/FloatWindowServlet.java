package mypetstore.servlets.ajax_necesory;

import com.alibaba.fastjson.JSON;
import mypetstore.domain.Category;
import mypetstore.domain.Product;
import mypetstore.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FloatWindowServlet extends HttpServlet {
    private CategoryService categoryService = new CategoryService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int index = request.getParameter("category").indexOf("=");
        String category = request.getParameter("category").substring(index+1);
        List<Product> productList = categoryService.getProductListByCategory(category);
        String jsonStr = JSON.toJSONString(productList);
        response.getWriter().print(jsonStr);
    }
}
