package com.example.shoppingmall.order;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    private Map<Integer, Order> orderTable = new HashMap();
    private int id = 0;

    public Order orderProduct(Order order) {
        order.setId(id++);
        orderTable.put(order.getId(), order);

        return orderTable.get(order.getId());
    }

    private int id(int i) {
        String formattedDateStr =
            java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("yyMMdd"))
                + String.format("%02d", i);
        return Integer.parseInt(formattedDateStr);
    }
}
