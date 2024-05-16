package com.example.shoppingmall.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    public void orderProduct(Order order) {
        this.orderRepository.orderProduct(order);
    }
}
