package com.example.springbootdemo.persistence;

import com.example.springbootdemo.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MBItemDAO {
//    void updateInventoryQuantity(Map<String, Object> param);
    int getInventoryQuantity(String itemId);
    List<Item> getItemListByProduct(String productId);
    Item getItem(String itemId);
    void updateInventoryQuantity(String itemId,int quantity);
}
