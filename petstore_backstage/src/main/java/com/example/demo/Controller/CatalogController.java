package com.example.demo.Controller;

import com.example.demo.domain.Account;
import com.example.demo.domain.Category;
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
@SessionAttributes({"categoryList"})
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
    public String categoryAdd_cancel(Model model){
        List<Category> categoryList = catalogService.getCategoryList();
        processCategoryDescription(categoryList);
        model.addAttribute("categoryList",categoryList);
        return "catalog/categoryList";
    }

    //修改Category
    @GetMapping("/CategoryModify")
    public String categoryAdd(Model model,String categoryId) {
        Category category = catalogService.getCategory(categoryId);
        model.addAttribute("category",category);
       return "catalog/CategoryModify";
    }

    //确认修改Category
    @PostMapping("/categoryModify_sure")
    public String categoryModify_sure(Model model) {
        //成功修改
        String message = "Add Successfully";
        model.addAttribute("message",message);
        return "catalog/categoryList";
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
