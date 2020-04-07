package com.example.springbootdemo.service;

import com.example.springbootdemo.domain.Order;

import java.util.List;

public interface OrderService {
    public void insertOrder(Order order);
    public int getNextId(String name);
    public Order getOrder(int orderId);
    public List<Order> getOrdersByUsername(String username);
    public void updateOrderStatus(String status, int orderId);
    String selectStatus(int orderId);
    void updateInventoryQuantity(String itemId,int quantity);
}
