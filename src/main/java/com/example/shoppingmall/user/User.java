package com.example.shoppingmall.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Component
public class User {

    private int id;
    @JsonProperty(value = "user_id")
    private String userId;
    private String pw;
    private String name;
    private String email;
    private String contact;

    public User convertToDTO(UserDTO userDto) {
        this.userId = userDto.getUserId();
        this.pw = userDto.getPw();
        this.name = userDto.getName();
        this.email = userDto.getEmail();
        this.contact = userDto.getContact();

        return this;
    }
}
