package com.example.shoppingmall.exception;

public class DuplicateUserIdException extends RuntimeException {

    private String message;

    public DuplicateUserIdException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
