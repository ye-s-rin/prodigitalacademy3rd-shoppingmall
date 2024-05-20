package com.example.shoppingmall.utils;

import static com.example.shoppingmall.utils.ApiUtils.error;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 유효성 검사에서 에러가 발생하면 호출되는 예외 처리 메소드
    @ExceptionHandler//(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiUtils.ApiResult<Map<String, String>> handleValidationExceptions(
        MethodArgumentNotValidException errors) {
        Map<String, String> errorMessages = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            errorMessages.put(error.getField(), error.getDefaultMessage());
        }

        return error(errorMessages, HttpStatus.BAD_REQUEST);
    }
}
