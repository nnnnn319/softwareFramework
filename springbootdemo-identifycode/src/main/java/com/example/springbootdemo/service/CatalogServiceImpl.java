package com.example.springbootdemo.service;

import com.example.springbootdemo.domain.Catagory;
import com.example.springbootdemo.domain.Item;
import com.example.springbootdemo.domain.Product;
import com.example.springbootdemo.persistence.ItemDAO;
import com.example.springbootdemo.persistence.MBItemDAO;
import com.example.springbootdemo.persistence.MBProductDAO;
import com.example.springbootdemo.persistence.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CatalogServiceImpl implements CatalogService {
    @Autowired
    ProductDAO productDAO;
    @Autowired
    ItemDAO itemDAO;
    @Autowired
    MBProductDAO mbProductDAO;
    @Autowired
    MBItemDAO mbItemDAO;

    @Override
    public Catagory getCategory(String categoryId) {
        return null;
    }


    @Override
    public List<Product> getProductListByCategory(String categoryId) {
//        return productDAO.getProductListByCategory(categoryId);
//        System.out.println("category");
        return mbProductDAO.getProductListByCategory(categoryId);
    }

    @Override
    public List<Item> getItemListByProduct(String productId) {
        return mbItemDAO.getItemListByProduct(productId);
    }

    @Override
    public Item getItem(String itemId)  {
        return mbItemDAO.getItem(itemId);
    }

    @Override
    public Product getProduct(String productId)  {
        return mbProductDAO.getProduct(productId);
    }

    @Override
    public boolean isItemInStock(String itemId)  {
        return mbItemDAO.getInventoryQuantity(itemId) > 0;
    }

    @Override
    public List<Product> searchProductList(String keyword) {
        return mbProductDAO.searchProductList("%" + keyword.toLowerCase() + "%");
    }

    @Override
    public int getQuantity(String itemId) {
        return mbItemDAO.getInventoryQuantity(itemId);
    }
}
