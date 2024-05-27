package com.example.shoppingmall.order;

import org.springframework.stereotype.Component;

@Component
public interface OrderRepository {

    void save(Order order);
}
