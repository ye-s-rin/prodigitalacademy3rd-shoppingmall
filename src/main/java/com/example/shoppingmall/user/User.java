package com.example.shoppingmall.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
public class User {

    private String userId;
    private String profileInfo;
    private String name;
    private String email;
    private String contact;
}
