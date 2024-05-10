package com.example.shoppingmall.product;

import java.util.Map;
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

    public Map findProducts(Integer currentPage, Integer limit) {
        if (limit == null) {
            limit = 0;
        }
        currentPage = currentPage > 0 ? currentPage : 1;
        return this.productRepository.findProducts(currentPage, limit);
    }

    public Map findProducts(Integer currentPage, Integer limit, Integer categoryId) {
        if (limit == null) {
            limit = 0;
        }
        currentPage = currentPage > 0 ? currentPage : 1;
        return this.productRepository.findProducts(currentPage, limit, categoryId);
    }

    public Product registerProduct(Product product) {
        log.info("/product: service - " + product.getName());
        return productRepository.registerProduct(product);
    }
}
