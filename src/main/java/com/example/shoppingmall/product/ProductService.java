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

    public ResponseEntity deleteProducts(Map<String, ArrayList<Integer>> deleteRequest) {
        Product deleteProduct;
        ResponseEntity result = new ResponseEntity<>(HttpStatus.OK);

        for (int id : deleteRequest.get("productIds")) {
            if (Validator.isNumber(id)) {
                deleteProduct = this.productRepository.deleteProduct(id);

                if (deleteProduct == null) {
                    result = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                result = new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }

        return result;
    }
}
