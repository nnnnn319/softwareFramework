package com.example.springbootdemo.persistence;

import com.example.springbootdemo.domain.LineItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MBLineItemDAO {
    List<LineItem> getLineItemsByOrderId(int orderId);
    void insertLineItem(LineItem lineItem);
}
