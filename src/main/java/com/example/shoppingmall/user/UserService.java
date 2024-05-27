package com.example.shoppingmall.user;

import com.example.shoppingmall.exception.PasswordNotValidException;
import jakarta.transaction.Transactional;
import java.util.NoSuchElementException;
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
    public String login(UserLoginReq userLoginReq) throws PasswordNotValidException {
        /**
         * 1) userID로 DB를 조회
         * - 만약 없으면? 예외 "아이디가 존재하지 않습니다."
         * 2) pw 비교
         * - 만약 틀리면? 예외 "비밀번호가 틀렸습니다."
         * 3) 성공? 로그인한 유저 이름 반환
         */
        User loginUser = userJPARepository.findByUserId(userLoginReq.getUserId())
            .orElseThrow(()
                -> new NoSuchElementException("아이디가 존재하지 않습니다."));

        if (isRightPw(userLoginReq, loginUser)) {
            return loginUser.getName();
        } else {
            throw new PasswordNotValidException("비밀번호가 틀렸습니다.");
        }
    }

    private static boolean isRightPw(UserLoginReq userLoginReq, User loginUser) {
        return userLoginReq.getPw().equals(loginUser.getPw());
    }

    public boolean isDuplicateUserId(String userId) {
        Optional<User> existUser = this.userJPARepository.findByUserId(userId);

        return existUser.isEmpty() ? false : true;
    }

    public User findByLoginInfo(UserLoginReq userLoginReq) {
        String userId = userLoginReq.getUserId();
        String pw = userLoginReq.getPw();
        Optional<User> user = this.userJPARepository.findByUserIdAndPw(userId, pw);
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