package com.example.demo.service;

import com.example.demo.domain.Category;
import com.example.demo.domain.Item;
import com.example.demo.domain.Product;
import com.example.demo.persistence.CategoryMapper;
import com.example.demo.persistence.ItemMapper;
import com.example.demo.persistence.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CatalogService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ItemMapper itemMapper;

    public List<Product> getProductListByCategory(String categoryId) {
        return productMapper.getProductListByCategory(categoryId);
    }

    public List<Category> getCategoryList() {//
        return categoryMapper.getCategoryList();
    }

    public Category getCategory(String categoryId) {
        return categoryMapper.getCategory(categoryId);
    }

    public Product getProduct(String productId) {
        return productMapper.getProduct(productId);
    }

    public List<Item> getItemListByProduct(String productId) {
        return itemMapper.getItemListByProduct(productId);
    }

    public Item getItem(String itemId) {
        return itemMapper.getItem(itemId);
    }

    public void updateCategoryName(String newName, String newDesn, String categoryId){
        categoryMapper.updateCategoryName(newName, newDesn,categoryId);
    }
    public void updateProduct(String  productId, String name, String description){
        productMapper.updateProduct(productId, name, description);
    }
    public void addProduct(String productId, String category, String name, String descn) {
        productMapper.addProduct(productId, category, name, descn);
    }
    public void  deleteProduct(String productId){
        productMapper.deleteProduct(productId);
    }

    public void deleteCategory(String catid) {
        productMapper.deleteProductByCategory(catid);
        categoryMapper.deleteCategory(catid);
    }

    public void addCategory(String description, String name, String catId) {
        categoryMapper.addCategory(description, name, catId);
    }

    public int getQuantity(String itemId) {
        return itemMapper.getInventoryQuantity(itemId);
    }

    public void updateItem(String itemId, String attribute1, BigDecimal listPrice){
        itemMapper.updateItem(itemId, attribute1, listPrice);
    }

    public void updateQuantity(String itemId, int quantity) {
        itemMapper.updateQuantity(itemId, quantity);
    }

    public void deleteItem(String itemId) {
        itemMapper.deleteItem(itemId);
    }

    public void addItem(Item item) {
        itemMapper.insertItem(item);
        itemMapper.insertItemQuantity(item);
    }
}
