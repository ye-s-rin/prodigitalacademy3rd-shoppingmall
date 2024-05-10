package com.example.shoppingmall.member;

import com.example.shoppingmall.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {

    private ProductRepository productRepository;
}
