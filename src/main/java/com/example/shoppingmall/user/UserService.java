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
        this.userRepository.save(userDto.convertToEntity());

        return this.userRepository.findByUserId(userDto.getUserId()).convertToDTO();
    }

    @Transactional
    public UserDTO login(Map<String, String> loginInfo) {
        return this.userRepository.findByLoginInfo(loginInfo).convertToDTO();
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
