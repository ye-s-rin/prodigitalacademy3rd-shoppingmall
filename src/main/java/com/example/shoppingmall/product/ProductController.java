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
            ProductDTO resultProductDto = productService.findProduct(id);
            if (resultProductDto != null) {
                return success(resultProductDto);
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

        return products != null ? success(products) : error("찾지 못했습니다.", HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/products")
    public ApiUtils.ApiResult registerProduct(@RequestBody ProductDTO productDto) {
        // * 유효성 검사: name(영어), price(숫자)
        // 1) 조건문
        if (Validator.isAlpha(productDto.getName()) && Validator.isNumber(productDto.getPrice())) {
            ProductDTO savedProductDto = this.productService.registerProduct(productDto);
            try {
                log.info(savedProductDto.getName());
            } catch (NullPointerException e) {
                return error("등록하지 못했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return success(savedProductDto); // HttpStatus.CREATED
        } else {
            return error("잘못된 사용자 요청입니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/products/{id}")
    public ApiUtils.ApiResult deleteProduct(@PathVariable("id") int id) {
        if (Validator.isNumber(id)) {
            ProductDTO deleteProductDto = this.productService.deleteProduct(id);
            return deleteProductDto != null ? success(deleteProductDto): error("삭제하지 못했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return error("잘못된 사용자 요청입니다.", HttpStatus.BAD_REQUEST);
    }

    @Transactional
    @PostMapping(value = "/products/delete")
    public ApiUtils.ApiResult deleteProducts(
        @RequestBody Map<String, ArrayList<Integer>> deleteRequest) {
        for (int id : deleteRequest.get("product_ids")) {
            if (Validator.isNumber(id)) {
                ProductDTO deleteProductDto = this.productService.deleteProduct(id);

                if (deleteProductDto == null) {
                    return error("삭제하지 못했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return error("잘못된 사용자 요청입니다.", HttpStatus.BAD_REQUEST);
            }
        }

        return success(deleteRequest);
    }
}
