package com.example.shoppingmall.product;

import com.example.shoppingmall.utils.Validator;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
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
    public String registerProduct(@RequestBody Product product) {
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
            if (savedProduct != null) {
                log.info("/product: controller - " + product.getName());
                return product.getName() + " is registered\n";
            }
            message += product.getName() + " is not registered\n";
        }

        return message;
    }
}
