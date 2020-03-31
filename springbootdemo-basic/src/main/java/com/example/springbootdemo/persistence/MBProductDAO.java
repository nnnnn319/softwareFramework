package com.example.springbootdemo.persistence;

import com.example.springbootdemo.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MBProductDAO {
    List<Product> getProductListByCategory(String categoryId);
    Product getProduct(String productId);
    List<Product> searchProductList(String keywords);
}
