package com.example.springbootdemo.controller.product;

import com.example.springbootdemo.domain.Product;
import com.example.springbootdemo.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchProduct {
    @Autowired
    private CatalogService categoryService;
    @PostMapping("")
    public String SearchProduct(String keyword, Model model) {
        if (keyword != null) {
            List<Product> productList = categoryService.searchProductList(keyword);
            model.addAttribute("productList", productList);
        } else
            System.out.println("null");
        return "catalog/SearchProducts";
    }
}
