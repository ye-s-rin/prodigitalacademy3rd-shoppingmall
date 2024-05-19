package com.example.shoppingmall.user;

import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public UserDTO join(@RequestBody UserDTO userDto) {

        return new UserDTO().convertToEntity(
            this.userRepository.join(new User().convertToDTO(userDto)));
    }

    public User login(Map<String, String> loginInfo) {
        return this.userRepository.login(loginInfo);
    }

    public boolean isDuplicateId(String userId) {
//        return this.userRepository.isDuplicateId(userId);
        User existUser = this.userRepository.findById(userId);

        if (existUser == null) {
            return false;
        } else {
            return true;
        }
    }
}
