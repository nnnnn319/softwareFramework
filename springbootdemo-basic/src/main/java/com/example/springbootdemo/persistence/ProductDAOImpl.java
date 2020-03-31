package com.example.springbootdemo.persistence;

import com.example.springbootdemo.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ProductDAOImpl implements ProductDAO {
    @Autowired
    DataSource dataSource;
    public static final String SEARCH_PRODUCT_LIST =
            "select PRODUCTID, NAME, DESCN as description, CATEGORY as categoryId from PRODUCT WHERE lower(name) like ?";
    public static final String GET_PRODUCT =
            "SELECT PRODUCTID, NAME, DESCN as description, CATEGORY as categoryId FROM PRODUCT WHERE PRODUCTID = ?";
    public static final String GET_PRODUCT_LIST_BYCATAGORY =
            "SELECT PRODUCTID, NAME, DESCN as description, CATEGORY as categoryId FROM PRODUCT WHERE CATEGORY = ?";
    @Override
    public List<Product> getProductListByCategory(String categoryId) {
            List<Product> productList = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_LIST_BYCATAGORY);
            preparedStatement.setString(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Product product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setCategoryId(resultSet.getString(4));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                productList.add(product);
            }
            preparedStatement.close();
            resultSet.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public Product getProduct(String productId) {
        Product product = null;
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GET_PRODUCT);
            pStatement.setString(1,productId);
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setCategoryId(resultSet.getString(4));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
            }
            resultSet.close();
            pStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> searchProductList(String keywords) {
        List<Product> productList_search = new ArrayList<Product>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(SEARCH_PRODUCT_LIST);
            pStatement.setString(1,keywords);
            ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                Product product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setCategoryId(resultSet.getString(4));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                productList_search.add(product);
            }
            resultSet.close();
            pStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList_search;
    }
}
