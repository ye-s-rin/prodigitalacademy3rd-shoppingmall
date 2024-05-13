package com.example.shoppingmall.user;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ApiResult {

    private boolean success;
    private Map<String, Object> response;
    private Map<String, Object> error;

    public ApiResult join(User user) {
        if (user != null) {
            this.success = true;
            this.response = new HashMap<>();
            this.error = null;

            this.response.put("user_id", user.getUserId());
            this.response.put("status", HttpStatus.CREATED);
        } else {
            this.success = false;
            this.response = null;
            this.error = new HashMap<>();

            this.error.put("message", "중복입니다.");
            this.error.put("status", HttpStatus.CONFLICT);
        }

        return this;
    }
}
