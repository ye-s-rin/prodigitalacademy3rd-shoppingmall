package com.example.shoppingmall.exception;

import static com.example.shoppingmall.utils.ApiUtils.error;

import com.example.shoppingmall.utils.ApiUtils.ApiResult;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 유효성 검사에서 에러가 발생하면 호출되는 예외 처리 메소드
    @ExceptionHandler//(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult<Map<String, String>> handleValidationExceptions(
        MethodArgumentNotValidException errors) {
        Map<String, String> errorMessages = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            errorMessages.put(error.getField(), error.getDefaultMessage());
        }

        log.info("errorMessages.toString={}", errorMessages.toString());
        return error(errorMessages, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResult<String> handleDuplicateUserIdException(
        DuplicateUserIdException errors) {
        String errorMessage = errors.getMessage();

        log.info("errorMessage.toString={}", errorMessage.toString());
        return error(errorMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult<String> handlePasswordNotValidException(
        PasswordNotValidException errors) {
        String errorMessage = errors.getMessage();

        log.info("errorMessage.toString={}", errorMessage.toString());
        return error(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
