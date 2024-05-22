package com.example.shoppingmall.product;

import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public void makeConnection() {
        this.productRepository.makeConnection();
    }

    public ProductDTO findProduct(int id) {
        return this.productRepository.findProduct(id).convertToDTO();
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

    public ProductDTO registerProduct(Product product) {
        return this.productRepository.registerProduct(product).convertToDTO();
    }

    public ProductDTO deleteProduct(int id) {
        return this.productRepository.deleteProduct(id).convertToDTO();
    }
}
