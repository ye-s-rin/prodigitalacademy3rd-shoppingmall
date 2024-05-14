package com.example.shoppingmall.user;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping(value = "/join/res/en") // Before
    public ResponseEntity joinByResponseEntity(@RequestBody User user) {
        /**
         * ID 중복 체크
         * 중복이면, 사용자 예외 클래스 소환
         * 1) 예외 클래스한테 return 요청
         * 2) 예외만 발생시키고 메시지는 직접 return
         */
        if (isDuplicateId(user)) {
            DuplicateUserIdException e = new DuplicateUserIdException();
            log.info(e.getMessage());

//            return new ResponseEntity(null, HttpStatus.CONFLICT);
            return new ResponseEntity(new ApiResult(null), HttpStatus.CONFLICT);
        }

        User signedUser = this.userService.join(user);

        if (signedUser != null) {
            Map<String, String> result = new HashMap<>();
            result.put("user_id", signedUser.getUserId());
//            return new ResponseEntity(result, HttpStatus.CREATED);
            return new ResponseEntity(new ApiResult(result), HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/join")
    public ResponseEntity join(@RequestBody User user) {
        /**
         * ID 중복 체크
         * 중복이면, 사용자 예외 클래스 소환
         * 1) 예외 클래스한테 return 요청
         * 2) 예외만 발생시키고 메시지는 직접 return
         */
        if (isDuplicateId(user)) {
            DuplicateUserIdException e = new DuplicateUserIdException();
            log.info(e.getMessage());

//            return new ResponseEntity(null, HttpStatus.CONFLICT);
            return new ResponseEntity(new ApiResult(null), HttpStatus.CONFLICT);
        }

        User signedUser = this.userService.join(user);

        if (signedUser != null) {
            Map<String, String> result = new HashMap<>();
            result.put("user_id", signedUser.getUserId());
//            return new ResponseEntity(result, HttpStatus.CREATED);
            return new ResponseEntity(new ApiResult(signedUser), HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    private boolean isDuplicateId(User user) {
        return this.userService.isDuplicateId(user.getUserId());
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
