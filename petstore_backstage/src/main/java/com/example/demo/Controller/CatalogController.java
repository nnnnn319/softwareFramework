package com.example.demo.Controller;

import com.example.demo.domain.Account;
import com.example.demo.domain.Category;
import com.example.demo.domain.Item;
import com.example.demo.domain.Product;
import com.example.demo.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.thymeleaf.model.IModel;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/catalog")
@SessionAttributes({"categoryList","productList","category"})
public class CatalogController {
    @Autowired
    private CatalogService catalogService;

    //首页
    @GetMapping("/view")
    public String index() {
        return "catalog/main";
    }

    //新增、修改、查询、删除Category页面
    @GetMapping("/viewCategoryList")
    public String categoryList(Model model, HttpSession session) {
        Account account = (Account)session.getAttribute("account");
        if(account == null) {
            String message = "Please login first";
            model.addAttribute("message",message);
            return "account/signOnForm";
        }
        List<Category> categoryList = catalogService.getCategoryList();
        processCategoryDescription(categoryList);
        model.addAttribute("categoryList",categoryList);
        return "catalog/categoryList";
    }

    //删除Category
    @GetMapping("/categoryDelete")
    public String categoryDelete(Model model) {
        String message = "Delete Successfully";
        model.addAttribute("message",message);
        return "catalog/categoryList";
    }

    //添加Category
    @GetMapping("/categoryAdd")
    public String categoryAdd() {
        return "catalog/newCategory";
    }

    //确定添加Category
    @PostMapping("/categoryAdd_sure")
        public String categoryAdd_sure(Model model){
        //成功添加
        String message = "Add Successfully";
        model.addAttribute("message",message);
        return "catalog/categoryList";
    }

    //取消
    @GetMapping("CategoryCancel")
    public String category_cancel(Model model){
        List<Category> categoryList = catalogService.getCategoryList();
        processCategoryDescription(categoryList);
        model.addAttribute("categoryList",categoryList);
        return "catalog/categoryList";
    }

    //修改Category
    @GetMapping("/CategoryModify")
    public String categoryModify(Model model,String categoryId) {
        Category category = catalogService.getCategory(categoryId);
        model.addAttribute("category",category);
       return "catalog/CategoryModify";
    }

    //确认修改Category
    @PostMapping("/categoryModify_sure")
    public String categoryModify_sure(Model model) {
        //成功修改
        String message = "Modify Successfully";
        model.addAttribute("message",message);
        return "catalog/categoryList";
    }

    //查看Product
    @GetMapping("/viewCategory")
    public String viewCategory(String categoryId, Model model) {
        if (categoryId != null) {
            List<Product> productList = catalogService.getProductListByCategory(categoryId);
            Category category = catalogService.getCategory(categoryId);
            processProductDescription(productList);
            model.addAttribute("productList", productList);
            model.addAttribute("category", category);
        }
        return "catalog/category";
    }

    //删除Product
    @GetMapping("/productDelete")
    public String productDelete(Model model) {

        //成功删除
        String message = "Delete Successfully";
        model.addAttribute("message",message);
        return "catalog/category";
    }

    //添加product
    @GetMapping("/productAdd")
    public String productAdd() {
        return "catalog/newProduct";
    }

    //确定添加product
    @PostMapping("/productAdd_sure")
    public String productAdd_sure(Model model){
        //成功添加
        String message = "Add Successfully";
        model.addAttribute("message",message);
        return "catalog/category";
    }

    //取消
    @GetMapping("ProductCancel")
    public String product_cancel(Model model){
        List<Category> categoryList = catalogService.getCategoryList();
        processCategoryDescription(categoryList);
        model.addAttribute("categoryList",categoryList);
        return "catalog/category";
    }

    //修改Product
    @GetMapping("/ProductModify")
    public String productModify(Model model,String productId) {
        Product product = catalogService.getProduct(productId);
        processProductDescription(product);
        model.addAttribute("product",product);
        return "catalog/ProductModify";
    }

    //确认修改Product
    @PostMapping("/productModify_sure")
    public String productModify_sure(Model model) {
        //成功修改
        String message = "Modify Successfully";
        model.addAttribute("message",message);
        return "catalog/category";
    }

    //查看Item
    @GetMapping("/viewProduct")
    public String viewProduct(String productId, Model model) {
        if (productId != null) {
            List<Item> itemList = catalogService.getItemListByProduct(productId);
            Product product = catalogService.getProduct(productId);
            model.addAttribute("product", product);
            model.addAttribute("itemList", itemList);
        }
        return "catalog/product";
    }





    private void processProductDescription(Product product){
        String [] temp = product.getDescription().split("\"");
        product.setDescriptionImage("../css/"+temp[1]);
        product.setDescriptionText(temp[2].substring(1));
    }

    private void processCategoryDescription(List<Category> categoryList){
        for(Category category : categoryList) {
            processCategoryDescription(category);
        }
    }

    private void processCategoryDescription(Category category){
        String [] temp = category.getDescription().split("\"");
        category.setDescription("../css/"+temp[1]);
    }

    private void processProductDescription(List<Product> productList){
        for(Product product : productList) {
            processProductDescription(product);
        }
    }
}
