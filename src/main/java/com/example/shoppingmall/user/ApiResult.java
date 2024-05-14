package com.example.shoppingmall.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ApiResult<T> {

    private boolean success;
    private T response;
    private ApiError error;

    public ApiResult(T response){
        this.response = response;

        if(this.response != null){
            this.success = true;
            this.error = null;
        } else{
            this.success = false;
            this.error = new ApiError();
        }
    }
}

class ApiError {

    private String message;
    private HttpStatus httpStatus;

    ApiError(){
        this.message = "중복입니다.";
        this.httpStatus = HttpStatus.CONFLICT;
    }
}