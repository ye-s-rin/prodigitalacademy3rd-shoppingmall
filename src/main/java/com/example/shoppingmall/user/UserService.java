package com.example.shoppingmall.user;

import jakarta.transaction.Transactional;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class UserService {

    private UserJPARepository userJPARepository;

    public void makeConnection() {
    }

    @Transactional
    public UserDTO join(@RequestBody UserDTO userDto) {
        this.userJPARepository.save(userDto.convertToEntity());

        Optional<User> joinUser = this.userJPARepository.findByUserId(userDto.getUserId());

        return joinUser != null ? joinUser.get().convertToDTO() : null;
    }

    @Transactional
    public UserDTO login(Map<String, String> loginInfo) {
        User loginUser = findByLoginInfo(loginInfo);

        return loginUser != null ? loginUser.convertToDTO() : null;
    }

    public boolean isDuplicateUserId(String userId) {
        Optional<User> existUser = this.userJPARepository.findByUserId(userId);

        return existUser.isEmpty() ? false : true;
    }

    public User findByLoginInfo(Map<String, String> loginInfo) {
        String userId = loginInfo.get("user_id");
        String pw = loginInfo.get("pw");
        Optional<User> user = userJPARepository.findByUserIdAndPw(userId, pw);
        return user.orElse(null);
    }

    public User findByUserId(String userId) {
        Optional<User> user = userJPARepository.findByUserId(userId);
        return user.orElse(null);
    }

    public User findById(int id) {
        Optional<User> user = userJPARepository.findById(id);
        return user.orElse(null);
    }
}
