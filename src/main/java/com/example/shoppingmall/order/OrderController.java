package com.example.shoppingmall.order;

import com.example.shoppingmall.product.ProductDTO;
import com.example.shoppingmall.product.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;
    private ProductService productService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/orders")
    public void orderProduct(@RequestBody OrderDTO orderDto) {
        ProductDTO orderdProductDto = this.productService.findProduct(orderDto.getProductId());

        // DTO -> Entity TODO: move to Service
        Order requestOrder = new Order (orderdProductDto.convertToEntity(), orderDto.getCount());
        this.orderService.orderProduct(requestOrder);
    }
}
