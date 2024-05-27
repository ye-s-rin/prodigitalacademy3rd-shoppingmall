package com.example.shoppingmall.exception;

public class PasswordNotValidException extends Throwable {

    private String message;

    public PasswordNotValidException(String message) {
        this.message = message;
    }
}
