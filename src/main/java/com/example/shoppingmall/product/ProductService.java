package com.example.shoppingmall.product;

import static com.example.shoppingmall.utils.Validator.checkRange;

import java.util.List;
import java.util.Optional;
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

    public ProductDTO findProduct(int id) {
        Optional<Product> product = productRepository.findById(id);
        return product.isEmpty() ? null : product.get().convertToDTO();
    }

    public Map<String, Object> findProducts(Integer currentPage, Integer limit,
        Integer categoryId) {

        currentPage = checkRange(currentPage);
        limit = checkRange(limit);

        if (currentPage != null && limit != null && categoryId != null) {
            return findProductsByCategoryId(currentPage, limit, categoryId);
        } else if (currentPage != null && limit != null && categoryId == null) {
            return findProductsByPaging(currentPage, limit);
        } else {
            return findAllProducts();
        }
    }

    public Map<String, Object> findAllProducts() {
        List<Product> products = productRepository.findAll();

        Map<String, Object> result = new HashMap<>();
        result.put("products", products);

        return result;
    }

    public Map<String, Object> findProductsByPaging(int currentPage, int limit) {
        Pageable pageable = PageRequest.of(currentPage - 1, limit);
        Page<Product> productPage = productRepository.findAll(pageable);
        int lastPage = Math.max(1, productPage.getTotalPages());

        if (currentPage > lastPage) {
            currentPage = lastPage;
            pageable = PageRequest.of(currentPage - 1, limit);
            productPage = productRepository.findAll(pageable);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("products", productPage.getContent());
        result.put("currentPage", productPage.getNumber() + 1);
        result.put("lastPage", productPage.getTotalPages());
        result.put("totalCount", productPage.getTotalElements());

        return result;
    }

    public Map<String, Object> findProductsByCategoryId(int currentPage, int limit,
        int categoryId) {
        Pageable pageable = PageRequest.of(currentPage - 1, limit);
        Page<Product> productPage = productRepository.findByCategoryId(categoryId, pageable);
        int lastPage = Math.max(1, productPage.getTotalPages());

        if (currentPage > lastPage) {
            currentPage = lastPage;
            pageable = PageRequest.of(currentPage - 1, limit);
            productPage = productRepository.findAll(pageable);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("products", productPage.getContent());
        result.put("currentPage", productPage.getNumber() + 1);
        result.put("lastPage", productPage.getTotalPages());
        result.put("totalCount", productPage.getTotalElements());

        return result;
    }

    public ProductDTO registerProduct(ProductDTO productDto) {
        return productRepository.save(productDto.convertToEntity()).convertToDTO();
    }

    public ProductDTO deleteProduct(int id) {
        Optional<Product> preProductDto = productRepository.findById(id);

        if (!preProductDto.isEmpty()) {
            productRepository.deleteById(id);
            Optional<Product> nowProductDto = productRepository.findById(id);

            return nowProductDto.isEmpty() ? preProductDto.get().convertToDTO() : null;
        } else {
            return null;
        }
    }
}