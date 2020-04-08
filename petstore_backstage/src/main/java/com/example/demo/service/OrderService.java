package com.example.demo.service;

import com.example.demo.domain.Item;
import com.example.demo.domain.LineItem;
import com.example.demo.domain.Order;
import com.example.demo.persistence.ItemMapper;
import com.example.demo.persistence.LineItemMapper;
import com.example.demo.persistence.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private LineItemMapper lineItemMapper;

    public List<Order> getOrdersByUsername(String username) {
        return orderMapper.getOrdersByUsername(username);
    }

    @Transactional
    public Order getOrder(int orderId) {
        Order order = orderMapper.getOrder(orderId);
        order.setLineItems(lineItemMapper.getLineItemsByOrderId(orderId));

        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            Item item = itemMapper.getItem(lineItem.getItemId());
            item.setQuantity(itemMapper.getInventoryQuantity(lineItem.getItemId()));
            lineItem.setItem(item);
        }
        return order;
    }

    public void sendGood(int orderId, String status) {
        orderMapper.updateOrderStatus(orderId, status);
    }

    public void deleteOrder(int orderId){
        orderMapper.deleteOrder(orderId);
        orderMapper.deleteOrderStatus(orderId);
    }
    public void updateOrder(Order order){
        orderMapper.updateOrder(order);
    }
}
