package com.example.shoppingmall.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class UserLoginReq {

    @JsonProperty(value = "user_id")
    @NotBlank(message = "아이디를 입력해주세요.")
    private String userId;
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*()\\-_=+{}|:;\"'<>,.?/~`]).{8,}$", message = "비밀번호는 특수기호, 숫자, 영어 문자를 포함한 최소 8자리여야 합니다.")
    private String pw;
}
