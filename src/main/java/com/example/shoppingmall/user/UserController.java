package com.example.shoppingmall.user;

import static com.example.shoppingmall.utils.ApiUtils.error;
import static com.example.shoppingmall.utils.ApiUtils.success;

import com.example.shoppingmall.exception.DuplicateUserIdException;
import com.example.shoppingmall.exception.PasswordNotValidException;
import com.example.shoppingmall.utils.ApiUtils;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/datasource")
    public void makeConnection() {
        this.userService.makeConnection();
    }

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

    @PostMapping(value = "/user/join")
    public ApiUtils.ApiResult join(@Valid @RequestBody UserDTO userDto) {
        if (isDuplicateUserId(userDto.getUserId())) {
            return error("이미 사용 중인 아이디입니다.", HttpStatus.CONFLICT);
        }

        UserDTO joinUser = this.userService.join(userDto);

        if (joinUser != null) {
            Map<String, String> result = new HashMap<>();
            result.put("id", String.valueOf(joinUser.getId()));
            result.put("user_id", joinUser.getUserId());

            return success(result);
        }

        return error("잘못된 사용자 요청입니다.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/users/duplication")
    public ApiUtils.ApiResult checkUsableUserId(@RequestBody Map<String, String> userInfo) {
        if (isDuplicateUserId(userInfo.get("user_id"))) {
            throw new DuplicateUserIdException("이미 사용 중인 아이디입니다.");
        } else {
            return success(userInfo);
        }
    }

    private boolean isDuplicateUserId(String userId) {
        return this.userService.isDuplicateUserId(userId) ? true : false;
    }

    @PostMapping(value = "/user/login")
    public ApiUtils.ApiResult login(@Valid @RequestBody UserLoginReq userLoginReq)
        throws PasswordNotValidException {
        String userName = this.userService.login(userLoginReq);

        return success(userName);
    }
}
