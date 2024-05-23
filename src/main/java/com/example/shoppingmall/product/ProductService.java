package com.example.shoppingmall.product;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public ProductDTO findById(int id) {
        return productRepository.findById(id).orElse(null).convertToDTO();
    }

    public Map<String, Object> findProducts(int currentPage, int limit) {
        Pageable pageable = PageRequest.of(currentPage - 1, limit);
        Page<Product> productPage = productRepository.findAll(pageable);

        Map<String, Object> result = new HashMap<>();
        result.put("products", productPage.getContent());
        result.put("currentPage", productPage.getNumber() + 1);
        result.put("lastPage", productPage.getTotalPages());
        result.put("totalCount", productPage.getTotalElements());

        return result;
    }

    public Map<String, Object> findProducts(int currentPage, int limit, int categoryId) {
        Pageable pageable = PageRequest.of(currentPage - 1, limit);
        Page<Product> productPage = productRepository.findByCategoryId(categoryId, pageable);

        Map<String, Object> result = new HashMap<>();
        result.put("products", productPage.getContent());
        result.put("currentPage", productPage.getNumber() + 1);
        result.put("lastPage", productPage.getTotalPages());
        result.put("totalCount", productPage.getTotalElements());

        return result;
    }

    public ProductDTO registerProduct(Product product) {
        return productRepository.save(product).convertToDTO();
    }

    public ProductDTO deleteProduct(int id) {
        productRepository.deleteById(id);
        return null;
    }
}
