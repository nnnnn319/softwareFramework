package mypetstore.servlets.ajax_necesory;

import com.alibaba.fastjson.JSON;
import mypetstore.domain.Product;
import mypetstore.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchAutoCompleteServlet extends HttpServlet {
    CategoryService categoryService = new CategoryService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keywords = request.getParameter("ajax_searchName");
        List<Product> productList = categoryService.searchProductList(keywords);
        List<Object> list = new ArrayList<Object>();
        for(int i=0;i<productList.size();i++){
            list.add(productList.get(i).getName());
        }
        String jsonStr = JSON.toJSONString(list);
        response.getWriter().print(jsonStr);
    }
}
