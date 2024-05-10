package com.example.shoppingmall.product;

import com.example.shoppingmall.utils.Validator;
import java.util.ArrayList;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Product> findProduct(@PathVariable("id") int id) {
        if (Validator.isNumber(id)) {
            log.info(id + "");
            Product resultProduct = productService.findProduct(id);
            if (resultProduct != null) {
                return new ResponseEntity<>(resultProduct, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/products")
    public ResponseEntity<Map> findProducts(
        @RequestParam(required = false, value = "currentPage") Integer currentPage,
        @RequestParam(required = false, value = "limit") Integer limit,
        @RequestParam(required = false, value = "categoryId") Integer categoryId) {

        log.info("currentPage: " + currentPage);
        log.info("limit: " + limit);
        log.info("categoryId: " + categoryId);

        Map products = this.productService.findProducts(currentPage, limit, categoryId);

        if (products != null) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/products")
    public ResponseEntity registerProduct(@RequestBody Product product) {
        // * 유효성 검사: name(영어), price(숫자)
        // 1) 조건문
        if (Validator.isAlpha(product.getName()) && Validator.isNumber(product.getPrice())) {
            Product savedProduct = this.productService.registerProduct(product);
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

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") int id) {
        return this.productService.deleteProduct(id);
    }

    @PostMapping(value = "/products/delete")
    public ResponseEntity deleteProducts(@RequestBody Map<String, ArrayList<Integer>> productsIds) {
        return this.productService.deleteProducts(productsIds);
    }
}
