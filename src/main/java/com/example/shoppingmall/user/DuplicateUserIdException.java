package com.example.shoppingmall.user;

public class DuplicateUserIdException extends RuntimeException {

    private String message;

    @Override
    public String getMessage() {
        return this.message;
    }
}
