package com.example.springbootdemo.service;

import com.example.springbootdemo.domain.Item;
import com.example.springbootdemo.domain.LineItem;
import com.example.springbootdemo.domain.Order;
import com.example.springbootdemo.domain.Sequence;
import com.example.springbootdemo.persistence.MBItemDAO;
import com.example.springbootdemo.persistence.MBLineItemDAO;
import com.example.springbootdemo.persistence.MBOrderDAO;
import com.example.springbootdemo.persistence.MBSequenceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private MBOrderDAO mbOrderDAO;
    @Autowired
    private MBLineItemDAO mbLineItemDAO;
    @Autowired
    private MBItemDAO mbItemDAO;
    @Autowired
    private MBSequenceDAO mbSequenceDAO;
    public void insertOrder(Order order) {
        order.setOrderId(getNextId("ordernum"));
        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            String itemId = lineItem.getItemId();
            Integer increment = new Integer(lineItem.getQuantity());
            Map<String, Object> param = new HashMap<String, Object>(2);
            param.put("itemId", itemId);
            param.put("increment", increment);
//            itemDAO.updateInventoryQuantity(param);
            mbItemDAO.updateInventoryQuantity(itemId,lineItem.getQuantity());
        }

        mbOrderDAO.insertOrder(order);
        mbOrderDAO.insertOrderStatus(order);
        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            lineItem.setOrderId(order.getOrderId());
            mbLineItemDAO.insertLineItem(lineItem);
        }
    }

    public int getNextId(String name) {
        Sequence sequence = new Sequence(name, -1);
        sequence = (Sequence) mbSequenceDAO.getSequence(sequence);
        if (sequence == null) {
            throw new RuntimeException("Error: A null sequence was returned from the database (could not get next " + name
                    + " sequence).");
        }
        Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
        mbSequenceDAO.updateSequence(parameterObject);
        return sequence.getNextId();
    }
    public Order getOrder(int orderId) {
        Order order = mbOrderDAO.getOrder(orderId);
        order.setLineItems(mbLineItemDAO.getLineItemsByOrderId(orderId));
        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            Item item = mbItemDAO.getItem(lineItem.getItemId());
            item.setQuantity(mbItemDAO.getInventoryQuantity(lineItem.getItemId()));
            lineItem.setItem(item);
        }

        return order;
    }

    @Override
    public List<Order> getOrdersByUsername(String username)  {
        return mbOrderDAO.getOrdersByUsername(username);
    }

}
