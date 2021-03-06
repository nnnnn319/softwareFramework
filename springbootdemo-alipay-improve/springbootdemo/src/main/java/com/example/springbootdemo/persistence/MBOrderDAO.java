package com.example.springbootdemo.persistence;

import com.example.springbootdemo.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MBOrderDAO {
    List<Order> getOrdersByUsername(String username);
    Order getOrder(int orderId);
    void insertOrder(Order order);
    void insertOrderStatus(Order order);
    void updateOrderStatus(String status, int orderId);
    String selectOrderStatus(int orderId);
}
