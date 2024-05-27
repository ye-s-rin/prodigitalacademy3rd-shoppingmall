package com.example.shoppingmall.order;

import com.example.shoppingmall.product.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Order {

    @Id
    private int id;

    @Column(name = "order_id")
    private int orderId;
    private Product product;
    private int count;

    public Order(Product product, int count){
        this.product = product;
        this.count = count;
    }
}
