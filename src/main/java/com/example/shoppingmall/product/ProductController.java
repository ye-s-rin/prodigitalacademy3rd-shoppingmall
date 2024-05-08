package com.example.shoppingmall.product;

import com.example.shoppingmall.utils.Validator;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping(value = "/products/{id}")
    public Product findProduct(@PathVariable("id") int id) {
        if (Validator.isNumber(id)) {
            return productService.findProduct(id);
        }
        return null;
    }

    @GetMapping(value = "/products")
    public Map findProducts(
        @RequestParam(required = false, value = "currentPage") Integer currentPage,
        @RequestParam(required = false, value = "limit") Integer limit) {
        if (currentPage == null) {
            return this.productService.findProducts();
        } else {
            return this.productService.pagination(currentPage, limit);
        }
    }

    @PostMapping(value = "/products")
    public ResponseEntity registerProduct(@RequestBody Product product) {
        // * 유효성 검사: name(영어), price(숫자)
        // 1) 조건문
        String message = "";

        if (!Validator.isAlpha(product.getName())) {
            message += "name is not alphabet.\n";
        }

        if (!Validator.isNumber(product.getPrice())) {
            message += "price is not number.\n";
        }

        if (Validator.isAlpha(product.getName()) && Validator.isNumber(product.getPrice())) {
            Product savedProduct = productService.registerProduct(product);
            try {
                log.info("/product: controller - " + savedProduct.getName());
            } catch (NullPointerException e) {
                message += product.getName() + " is not registered\n";
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
