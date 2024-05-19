package com.example.shoppingmall.user;

import static com.example.shoppingmall.utils.ApiUtils.error;
import static com.example.shoppingmall.utils.ApiUtils.success;

import com.example.shoppingmall.utils.ApiUtils;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

//    @PostMapping(value = "/join/res/en") // Before
//    public ResponseEntity joinByResponseEntity(@RequestBody User user) {
//        /**
//         * ID 중복 체크
//         * 중복이면, 사용자 예외 클래스 소환
//         * 1) 예외 클래스한테 return 요청
//         * 2) 예외만 발생시키고 메시지는 직접 return
//         */
//        if (isDuplicateId(user)) {
//            DuplicateUserIdException e = new DuplicateUserIdException();
//            log.info(e.getMessage());
//
//            return new ResponseEntity(null, HttpStatus.CONFLICT);
//            return new ResponseEntity(new ApiResult(null), HttpStatus.CONFLICT);
//        }
//
//        User joinedUser = this.userService.join(user);
//
//        if (joinedUser != null) {
//            Map<String, String> result = new HashMap<>();
//            result.put("user_id", joinedUser.getUserId());
//            return new ResponseEntity(result, HttpStatus.CREATED);
//            return new ResponseEntity(new ApiResult(result), HttpStatus.CREATED);
//        }
//        return new ResponseEntity(HttpStatus.BAD_REQUEST);
//    }

    @PostMapping(value = "/join")
    public ApiUtils.ApiResult join(@Valid @RequestBody UserDTO userDto,
        Errors errors) {
        /**
         * ID 중복 체크
         * 중복이면, 사용자 예외 클래스 소환
         * 1) 예외 클래스한테 return 요청
         * 2) 예외만 발생시키고 메시지는 직접 return
         */
        if (errors.hasErrors()) {
            List<FieldError> list = errors.getFieldErrors();
            String message = "";
            for (FieldError error : list) {
                message += error.getDefaultMessage() + " ";
            }
            return error(message, HttpStatus.BAD_REQUEST);
        }

        if (isDuplicateId(userDto)) {
            DuplicateUserIdException e = new DuplicateUserIdException();
            log.info(e.getMessage());

            return error("중복입니다.", HttpStatus.CONFLICT);
        }

        UserDTO joinedUser = this.userService.join(userDto);

        if (joinedUser != null) {
            Map<String, String> result = new HashMap<>();
            result.put("user_id", joinedUser.getUserId());

            return success(result);
        }

        return error("잘못된 사용자 요청입니다.", HttpStatus.BAD_REQUEST);
    }

    private boolean isDuplicateId(UserDTO userDto) {
        return this.userService.isDuplicateId(userDto.getUserId());
    }

    @PostMapping(value = "/duplication")
    public ResponseEntity isDuplicateId(@RequestBody Map<String, String> userId) {
        if (this.userService.isDuplicateId(userId.get("user_id"))) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody Map<String, String> loginInfo) {
        User loginedUser = this.userService.login(loginInfo);

        if (loginedUser != null) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
