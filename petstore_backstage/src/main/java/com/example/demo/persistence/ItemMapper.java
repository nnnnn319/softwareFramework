package com.example.demo.persistence;

import com.example.demo.domain.Item;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ItemMapper {
    int getInventoryQuantity(String itemId);

    Item getItem(String itemId);

    List<Item> getItemListByProduct(String productId);

    void updateItem(String itemId, String attribute1, BigDecimal listPrice);

    void updateQuantity(String itemId, int quantity);

    void deleteItem(String itemId);

    void insertItem(Item item);

    void insertItemQuantity(Item item);
}
