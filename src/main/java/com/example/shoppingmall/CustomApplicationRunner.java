package com.example.shoppingmall;

import com.example.shoppingmall.user.User;
import com.example.shoppingmall.user.UserJPARepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CustomApplicationRunner implements ApplicationRunner {

    @Autowired
    UserJPARepository userJPARepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Application main과 동시 실행");

        Optional<User> resultUser = this.userJPARepository.findByUserId("xxx");
        System.out.println(resultUser.isEmpty() ? null : resultUser.get().getUserId());
    }
}
