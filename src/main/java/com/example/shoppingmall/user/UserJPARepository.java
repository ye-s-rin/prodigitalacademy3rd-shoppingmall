package com.example.shoppingmall.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJPARepository extends JpaRepository<User, Integer> {

    /**
     * extends 인터페이스의 기본 메소드는 그대로 사용 -> 하이버네이트
     * 커스텀 메소드 (QueryByExampleExecutor)
     */

    Optional<User> findByUserId(String userId);
    Optional<User> findByUserIdAndPw(String userId, String pw);
}
