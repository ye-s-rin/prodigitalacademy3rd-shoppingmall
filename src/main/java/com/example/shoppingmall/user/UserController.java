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

    @PostMapping(value = "/join")
    public ResponseEntity join(@RequestBody User user){
        User signedUser = this.userService.join(user);

        if(signedUser != null) {
            Map<String, String> result = new HashMap<>();
            result.put("user_id", signedUser.getUser_id());
            return new ResponseEntity(result, HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody Map<String, String> loginInfo){
        User loginedUser = this.userService.login(loginInfo);

        if(loginedUser != null){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
