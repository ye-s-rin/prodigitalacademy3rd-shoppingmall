package com.example.shoppingmall.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
public class User {

    @JsonProperty(value = "user_id")
    private String userId;
    private String pw;
    private String name;
    private String email;
    private String contact;
}
