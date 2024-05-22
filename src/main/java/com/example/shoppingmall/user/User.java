package com.example.shoppingmall.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class User {

    @Id
    private int id;

    @Column(name = "user_id")
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

    public UserDTO convertToDTO() {
        return new UserDTO(this.userId, this.pw, this.name, this.email, this.contact);
    }
}
