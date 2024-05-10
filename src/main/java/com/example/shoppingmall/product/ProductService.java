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

    public Map findProducts(Integer currentPage, Integer limit, Integer categoryId) {
        if (limit == null) {
            limit = 0;
        }
        currentPage = currentPage > 0 ? currentPage : 1;

        Map products;
        if (currentPage == null) {
            products = this.productRepository.findProducts();
        } else if (categoryId == null) {
            products = this.productRepository.findProducts(currentPage, limit);
        } else {
            products = this.productRepository.findProducts(currentPage, limit, categoryId);
        }

        return products;
    }

    public Product registerProduct(Product product) {
        log.info("/product: service - " + product.getName());
        return productRepository.registerProduct(product);
    }
}
