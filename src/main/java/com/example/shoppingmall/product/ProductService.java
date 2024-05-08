package com.example.shoppingmall.product;

import java.util.Map;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Log
@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public Product findProduct(int id) {
        return productRepository.findProduct(id);
    }

    public Map findProducts() {
        return this.productRepository.findProducts();
    }

    public Map pagination(int currentPage, int limit) {
        return this.productRepository.pagination(currentPage, limit);
    }

    public Product registerProduct(Product product) {
        log.info("/product: service - " + product.getName());
        return productRepository.registerProduct(product);
    }
}
