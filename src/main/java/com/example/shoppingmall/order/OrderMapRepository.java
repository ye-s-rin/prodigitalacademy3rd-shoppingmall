package com.example.shoppingmall.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class OrderMapRepository {// implements OrderRepository{

    @PersistenceContext
    private EntityManager entityManager;
    private Map<Integer, Order> orderTable = new HashMap();
    private int id = 0;

    public void save(Order order) {
        this.entityManager.persist(order);

//        return findByOrderId(order.getOrderId());
    }

    private int orderId(int i) {
        String formattedDateStr =
            java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("yyMMdd"))
                + String.format("%02d", i);
        return Integer.parseInt(formattedDateStr);
    }
}
