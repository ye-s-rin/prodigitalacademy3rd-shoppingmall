package com.example.shoppingmall.user;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private Map<String, User> userTable = new HashMap<>();

    public User join(User user) {
        this.userTable.put(user.getUserId(), user);
        return this.userTable.get((user.getUserId()));
    }

    public User login(Map<String, String> loginInfo) {
        for (User user : this.userTable.values()) {
            if (isSignedUser(user, loginInfo)){
                return this.userTable.get(user.getUserId());
            }
        }
        return null;
    }

    private boolean isSignedUser(User user, Map<String, String> loginInfo) {
        return user.getUserId().equals(loginInfo.get("user_id")) && user.getPw()
            .equals(loginInfo.get("pw"));
    }

    public boolean isDuplicateId(String userId) {
        return this.userTable.containsKey(userId);
    }
}
