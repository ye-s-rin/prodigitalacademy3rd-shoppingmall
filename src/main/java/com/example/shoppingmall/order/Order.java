package com.example.shoppingmall.order;

import com.example.shoppingmall.product.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Order {

    private int id;
    private Product product;
    private int count;

    public Order(Product product, int count){
        this.product = product;
        this.count = count;
    }
}
