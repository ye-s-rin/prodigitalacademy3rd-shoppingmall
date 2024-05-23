package com.example.shoppingmall.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Product {

    @Id
    private int id;

    @Column(name = "product_id")
    private int productId;
    private String name;
    private int price;
    private String summary;
    private String description;

    @Column(name = "category_id")
    private int categoryId;

    public Product(int productId, String name, int categoryId) {
        this.productId = productId;
        this.name = name;
        this.categoryId = categoryId;
    }

    public Product(int productId, String name, int price, String summary, String description, int categoryId) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.summary = summary;
        this.description = description;
        this.categoryId = categoryId;
    }

    public ProductDTO convertToDTO() {
        return new ProductDTO(this.productId, this.name, this.price, this.summary, this.description,
            this.categoryId);
    }
}
