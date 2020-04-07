package com.example.demo.persistence;

import com.example.demo.domain.LineItem;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LineItemMapper {
    List<LineItem> getLineItemsByOrderId(int orderId);
}
