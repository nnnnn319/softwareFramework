package com.example.demo.persistence;

import com.example.demo.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemMapper {
    int getInventoryQuantity(String itemId);

    Item getItem(String itemId);
}
