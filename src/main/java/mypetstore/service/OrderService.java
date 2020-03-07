package mypetstore.service;

import mypetstore.domain.Item;
import mypetstore.domain.LineItem;
import mypetstore.domain.Order;
import mypetstore.domain.Sequence;
import mypetstore.persistence.ItemDAO;
import mypetstore.persistence.LineItemDAO;
import mypetstore.persistence.OrderDAO;
import mypetstore.persistence.SequenceDAO;
import mypetstore.persistence.impl.ItemDAOImpl;
import mypetstore.persistence.impl.LineItemDAOImpl;
import mypetstore.persistence.impl.OrderDAOImpl;
import mypetstore.persistence.impl.SequenceDAOImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAOImpl();
    private LineItemDAO lineItemDAO = new LineItemDAOImpl();
    private SequenceDAO sequenceDAO = new SequenceDAOImpl();
    private ItemDAO itemDAO = new ItemDAOImpl();
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
            itemDAO.updateInventoryQuantity(itemId,lineItem.getQuantity());
        }

        orderDAO.insertOrder(order);
        orderDAO.insertOrderStatus(order);
        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            lineItem.setOrderId(order.getOrderId());
            lineItemDAO.insertLineItem(lineItem);
        }
    }

    public Order getOrder(int orderId) {
        Order order = orderDAO.getOrder(orderId);
        order.setLineItems(lineItemDAO.getLineItemsByOrderId(orderId));
        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            Item item = itemDAO.getItem(lineItem.getItemId());
            item.setQuantity(itemDAO.getInventoryQuantity(lineItem.getItemId()));
            lineItem.setItem(item);
        }

        return order;
    }

    public List<Order> getOrdersByUsername(String username) {
        return orderDAO.getOrdersByUsername(username);
    }

    public int getNextId(String name) {
        Sequence sequence = new Sequence(name, -1);
        sequence = (Sequence) sequenceDAO.getSequence(sequence);
        if (sequence == null) {
            throw new RuntimeException("Error: A null sequence was returned from the database (could not get next " + name
                    + " sequence).");
        }
        Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
        sequenceDAO.updateSequence(parameterObject);
        return sequence.getNextId();
    }
}
