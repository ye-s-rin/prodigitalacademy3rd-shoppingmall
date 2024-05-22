package com.example.shoppingmall.user;

import jakarta.transaction.Transactional;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class UserServiceLegacy {

    private UserRepositoryLegacy userRepository;

    public void makeConnection() {
        this.userRepository.makeConnection();
    }

    @Transactional
    public UserDTO join(@RequestBody UserDTO userDto) {
        this.userRepository.save(userDto.convertToEntity());

        User joinUser = this.userRepository.findByUserId(userDto.getUserId());

        if (joinUser != null) {
            return joinUser.convertToDTO();
        } else {
            return null;
        }
    }

    @Transactional
    public UserDTO login(Map<String, String> loginInfo) {
        User loginUser = this.userRepository.findByLoginInfo(loginInfo);

        if (loginUser != null) {
            return loginUser.convertToDTO();
        } else {
            return null;
        }
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
