package com.example.shoppingmall.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;
    @JsonProperty(value = "user_id")
    private String userId;
    private String pw;
    private String name;
    private String email;
    private String contact;

    public User(String userId, String pw, String name, String email, String contact) {
        this.userId = userId;
        this.pw = pw;
        this.name = name;
        this.email = email;
        this.contact = contact;
    }

    public User convertToDTO(UserDTO userDto) {
        this.id = userDto.getId();
        this.userId = userDto.getUserId();
        this.pw = userDto.getPw();
        this.name = userDto.getName();
        this.email = userDto.getEmail();
        this.contact = userDto.getContact();

        return this;
    }
}
