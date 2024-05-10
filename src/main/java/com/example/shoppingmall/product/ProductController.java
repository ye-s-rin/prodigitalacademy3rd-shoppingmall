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
        return this.productService.findProduct(id);
    }

    @GetMapping(value = "/products")
    public ResponseEntity<Map> findProducts(
        @RequestParam(required = false, value = "currentPage") Integer currentPage,
        @RequestParam(required = false, value = "limit") Integer limit,
        @RequestParam(required = false, value = "categoryId") Integer categoryId) {

        Map products = this.productService.findProducts(currentPage, limit, categoryId);

        if (products != null) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/products")
    public ResponseEntity registerProduct(@RequestBody Product product) {
        return this.productService.registerProduct(product);
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") int id) {
        return this.productService.deleteProduct(id);
    }

    @PostMapping(value = "/products/delete")
    public ResponseEntity deleteProducts(@RequestBody Map<String, ArrayList<Integer>> deleteRequest) {
        return this.productService.deleteProducts(deleteRequest);
    }
}
