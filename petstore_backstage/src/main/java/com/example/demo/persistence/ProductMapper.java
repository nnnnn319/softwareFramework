package com.example.demo.persistence;

import com.example.demo.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {

    List<Product> getProductListByCategory(String categoryId);

    Product getProduct(String productId);

    void updateProduct(String  productId, String name, String description);

    void addProduct(String productId, String category, String name, String descn);

    void deleteProduct(String productId);

    void deleteProductByCategory(String categoryId);
}
