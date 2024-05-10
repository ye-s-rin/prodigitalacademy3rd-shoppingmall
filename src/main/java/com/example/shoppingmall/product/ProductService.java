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
        return this.productRepository.findProduct(id);
    }

    public Map findProducts(Integer currentPage, Integer limit, Integer categoryId) {
        limit = limit != null ? limit : 0;

        Map products;
        if (currentPage == null) {
            products = this.productRepository.findProducts();
        } else if (categoryId == null) {
            currentPage = currentPage > 0 ? currentPage : 1;
            products = this.productRepository.findProducts(currentPage, limit);
        } else {
            currentPage = currentPage > 0 ? currentPage : 1;
            products = this.productRepository.findProducts(currentPage, limit, categoryId);
        }

        return products;
    }

    public Product registerProduct(Product product) {
        return this.productRepository.registerProduct(product);
    }

    public Product deleteProduct(int id) {
        return this.productRepository.deleteProduct(id);
    }
}
