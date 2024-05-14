package com.example.shoppingmall.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ApiUtils {

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult(true, data, null);
    }

    public static ApiResult error(String message, HttpStatus httpStatus) {
        return new ApiResult(false, null, new ApiError(message, httpStatus));
    }

    @Getter
    @AllArgsConstructor
    public static class ApiResult<T> {

        private boolean success;
        private T response;
        private ApiError error;
    }

    @Getter
    @AllArgsConstructor
    static class ApiError {

        private String message;
        private HttpStatus httpStatus;
    }
}