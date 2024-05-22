package com.example.shoppingmall.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class ProductDTO {

    private int id;
    private int productId;
    private String name;
    private int price;
    private String summary;
    private String desc;
    private int categoryId;

    public ProductDTO(int productId, String name, int categoryId) {
        this.productId = productId;
        this.name = name;
        this.categoryId = categoryId;
    }

    public ProductDTO(int productId, String name, int price, String summary, String desc, int categoryId) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.summary = summary;
        this.desc = desc;
        this.categoryId = categoryId;
    }

    public Product convertToEntity() {
        return new Product(this.productId, this.name, this.price, this.summary, this.desc,
            this.categoryId);
    }
}
