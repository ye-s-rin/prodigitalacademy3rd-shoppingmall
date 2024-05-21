package com.example.shoppingmall.user;

import jakarta.transaction.Transactional;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public void makeConnection() {
        this.userRepository.makeConnection();
    }

    @Transactional
    public UserDTO join(@RequestBody UserDTO userDto) {
        return this.userRepository.save(userDto.convertToEntity()).convertToDTO();
    }

    @Transactional
    public UserDTO login(Map<String, String> loginInfo) {
        return this.userRepository.login(loginInfo).convertToDTO();
    }

    public boolean isDuplicateUserId(String userId) {
        User existUser = this.userRepository.findByUserId(userId);

        if (existUser != null) {
            return true;
        } else {
            return false;
        }
    }
}
