package com.example.shoppingmall.member;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Member {

    private String user_id;
    private String profile_info;
    private String name;
    private String email;
    private String contact;
}
