package com.example.springbootdemo.service;

import com.example.springbootdemo.domain.Catagory;
import com.example.springbootdemo.domain.Item;
import com.example.springbootdemo.domain.Product;

import java.util.List;

public interface CatalogService {
    public Catagory getCategory(String categoryId);
    public List<Product> getProductListByCategory(String categoryId);
    public List<Item> getItemListByProduct(String productId);
    public Item getItem(String itemId);
    public Product getProduct(String productId);
    public boolean isItemInStock(String itemId);
    public List<Product> searchProductList(String keyword);
}
