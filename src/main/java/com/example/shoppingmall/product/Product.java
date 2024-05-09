package com.example.shoppingmall.product;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Product {

    private int id;
    private String name;
    private int price;
    private String summary;
    private String desc;
    private int categoryId;

    public Product(){}

    public Product(int id, String name, int categoryId){
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
    }
}
