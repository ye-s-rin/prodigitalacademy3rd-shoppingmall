package com.example.shoppingmall.product;

import static com.example.shoppingmall.utils.ApiUtils.error;
import static com.example.shoppingmall.utils.ApiUtils.success;

import com.example.shoppingmall.utils.ApiUtils;
import com.example.shoppingmall.utils.Validator;
import jakarta.transaction.Transactional;
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
    public ApiUtils.ApiResult findProduct(@PathVariable("id") int id) {
        if (Validator.isNumber(id)) {
            Product resultProduct = productService.findProduct(id);
            if (resultProduct != null) {
                return success(resultProduct);
            } else {
                return error("찾지 못했습니다.", HttpStatus.NOT_FOUND);
            }
        }
        return error("잘못된 사용자 요청입니다.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/products")
    public ApiUtils.ApiResult findProducts(
        @RequestParam(required = false, value = "currentPage") Integer currentPage,
        @RequestParam(required = false, value = "limit") Integer limit,
        @RequestParam(required = false, value = "categoryId") Integer categoryId) {

        Map products = this.productService.findProducts(currentPage, limit, categoryId);

        if (products != null) {
            return success(products);
        } else {
            return error("찾지 못했습니다.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/products")
    public ApiUtils.ApiResult registerProduct(@RequestBody Product product) {
        // * 유효성 검사: name(영어), price(숫자)
        // 1) 조건문
        if (Validator.isAlpha(product.getName()) && Validator.isNumber(product.getPrice())) {
            Product savedProduct = this.productService.registerProduct(product);
            try {
                log.info(savedProduct.getName());
            } catch (NullPointerException e) {
                return error("등록하지 못했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return success(savedProduct); // HttpStatus.CREATED
        } else {
            return error("잘못된 사용자 요청입니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/products/{id}")
    public ApiUtils.ApiResult deleteProduct(@PathVariable("id") int id) {
        if (Validator.isNumber(id)) {
            Product deleteProduct = this.productService.deleteProduct(id);
            if (deleteProduct != null) {
                return success(deleteProduct);
            } else {
                return error("삭제하지 못했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return error("잘못된 사용자 요청입니다.", HttpStatus.BAD_REQUEST);
    }

    @Transactional
    @PostMapping(value = "/products/delete")
    public ApiUtils.ApiResult deleteProducts(
        @RequestBody Map<String, ArrayList<Integer>> deleteRequest) {
        for (int id : deleteRequest.get("productIds")) {
            if (Validator.isNumber(id)) {
                Product deleteProduct = this.productService.deleteProduct(id);

                if (deleteProduct == null) {
                    return error("삭제하지 못했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return error("잘못된 사용자 요청입니다.", HttpStatus.BAD_REQUEST);
            }
        }

        return success(deleteRequest.get("productIds"));
    }
}
