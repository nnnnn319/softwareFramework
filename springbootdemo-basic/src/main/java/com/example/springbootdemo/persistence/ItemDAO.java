package com.example.springbootdemo.persistence;

import com.example.springbootdemo.domain.Item;

import java.util.List;
import java.util.Map;

public interface ItemDAO {
    void updateInventoryQuantity(Map<String, Object> param);
    int getInventoryQuantity(String itemId);
    List<Item> getItemListByProduct(String productId);
    Item getItem(String itemId);
    void updateInventoryQuantity(String itemId,int quantity);
}
