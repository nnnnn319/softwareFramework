package com.example.springbootdemo.controller.product;

import com.example.springbootdemo.domain.Item;
import com.example.springbootdemo.domain.Product;
import com.example.springbootdemo.service.CatalogService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ShowProduct {
    @Autowired
    private CatalogService catalogService;
    @GetMapping("/get")
    String GetProduct(String categoryId, Model model){
        List<Product> productList = catalogService.getProductListByCategory(categoryId);
        model.addAttribute("productList", productList);
        return "catalog/Product";
    }
    @GetMapping("/item")
    String GetItem(String productId, Model model) {
        List<Item>itemList = catalogService.getItemListByProduct(productId);
        model.addAttribute("itemList", itemList);
        return "catalog/Item";
    }
    @GetMapping("/specificitem")
    String GetSpeItem(String itemId, Model model) {
        Item item = catalogService.getItem(itemId);
        Product product = item.getProduct();
        model.addAttribute("item", item);
        model.addAttribute("product", product);
        return "catalog/SpecificItem";
    }
}
