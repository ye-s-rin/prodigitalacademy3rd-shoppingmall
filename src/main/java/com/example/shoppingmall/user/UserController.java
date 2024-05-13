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
        if(this.userService.isDuplicated(user.getUserId())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        User signedUser = this.userService.join(user);

        if(signedUser != null) {
            Map<String, String> result = new HashMap<>();
            result.put("user_id", signedUser.getUserId());
            return new ResponseEntity(result, HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/duplication")
    public ResponseEntity isDuplicated(@RequestBody Map<String, String> userId){
        if(this.userService.isDuplicated(userId.get("user_id"))){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity(HttpStatus.OK);
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
