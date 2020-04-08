package com.example.demo.persistence;

import com.example.demo.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    List<Order> getOrdersByUsername(String username);

    Order getOrder(int orderId);

    void updateOrderStatus(int orderId, String status);

    void deleteOrder(int orderId);

    void deleteOrderStatus(int orderId);

    void updateOrder(Order order);
}
