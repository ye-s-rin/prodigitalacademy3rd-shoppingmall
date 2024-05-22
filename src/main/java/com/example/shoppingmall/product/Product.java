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
    private String name;
    private int price;
    private String summary;
    private String desc;
    @Column(name = "category_id")
    private int categoryId;

    public Product(int id, String name, int categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
    }

    public Product(int id, String name, int price, String summary, String desc, int categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.summary = summary;
        this.desc = desc;
        this.categoryId = categoryId;
    }

    public ProductDTO convertToDTO() {
        return new ProductDTO(this.id, this.name, this.price, this.summary, this.desc,
            this.categoryId);
    }
}
