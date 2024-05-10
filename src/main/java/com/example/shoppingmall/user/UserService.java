package com.example.shoppingmall.user;

import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User signup(@RequestBody User user){
        return this.userRepository.signup(user);
    }

    public User login(Map<String, String> loginInfo) {
        return this.userRepository.login(loginInfo);
    }
}
