package org.csu.mypetstore.controller;

import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;


@Controller
@RequestMapping("/catalog")
@SessionAttributes({"account"})
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/view")
    public String index() {
        return "catalog/main";
    }

    @GetMapping("/viewCategory")
    public String viewCategory(String categoryId, Model model) {
        if (categoryId != null) {
            List<Product> productList = catalogService.getProductListByCategory(categoryId);
            Category category = catalogService.getCategory(categoryId);
            model.addAttribute("productList", productList);
            model.addAttribute("category", category);
        }
        return "catalog/category";
    }

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

    @GetMapping("/viewItem")
    public String viewItem(String itemId, Model model) {
        Item item = catalogService.getItem(itemId);
        Product product = item.getProduct();
        processProductDescription(product);
        model.addAttribute("item", item);
        model.addAttribute("product", product);
        return "catalog/item";
    }

    @PostMapping("/searchProducts")
    public String searchProducts(String keyword, Model model) {
        if (keyword == null || keyword.length() < 1) {
            model.addAttribute("message","Please enter a keyword to search for, then press the search button.");
            return "common/error";
        } else {
            List<Product> productList = catalogService.searchProductList(keyword.toLowerCase());
            processProductDescription(productList);
            model.addAttribute("productList",productList);
            return "catalog/searchProducts";
        }
    }

    private void processProductDescription(Product product){
        String [] temp = product.getDescription().split("\"");
        product.setDescriptionImage("../css/"+temp[1]);
        product.setDescriptionText(temp[2].substring(1));
    }

    private void processProductDescription(List<Product> productList){
        for(Product product : productList) {
            processProductDescription(product);
        }
    }
}