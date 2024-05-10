package com.example.shoppingmall.product;

import com.example.shoppingmall.utils.Validator;
import java.util.ArrayList;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Log
@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public Product findProduct(int id) {
        return productRepository.findProduct(id);
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

    public ResponseEntity registerProduct(Product product) {
        // * 유효성 검사: name(영어), price(숫자)
        // 1) 조건문
        if (Validator.isAlpha(product.getName()) && Validator.isNumber(product.getPrice())) {
            Product savedProduct = this.productRepository.registerProduct(product);
            try {
                log.info(savedProduct.getName());
            } catch (NullPointerException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity deleteProduct(int id) {
        if (Validator.isNumber(id)) {
            Product deleteProduct = this.productRepository.deleteProduct(id);
            if (deleteProduct == null) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity deleteProducts(Map<String, ArrayList<Integer>> productsIds) {
        Product deleteProduct;
        ResponseEntity result = new ResponseEntity<>(HttpStatus.OK);

        for (int id : productsIds.get("productIds")) {
            if (Validator.isNumber(id)) {
                deleteProduct = this.productRepository.deleteProduct(id);

                if (deleteProduct != null) {
                    result = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                result = new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }

        return result;
    }
}
