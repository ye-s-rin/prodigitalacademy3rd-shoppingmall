package com.example.shoppingmall.user;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private Map<String, User> userTable = new HashMap<>();

    public User join(User user) {
        this.userTable.put(user.getUser_id(), user);
        return this.userTable.get((user.getUser_id()));
    }

    public User login(Map<String, String> loginInfo) {
        for (User user : this.userTable.values()) {
            if (isSignedUser(user, loginInfo)){
                return this.userTable.get(user.getUser_id());
            }
        }
        return null;
    }

    private boolean isSignedUser(User user, Map<String, String> loginInfo) {
        return user.getUser_id().equals(loginInfo.get("user_id")) && user.getProfile_info()
            .equals(loginInfo.get("profile_info"));
    }
}
