package com.example.shoppingmall.user;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private Map<Integer, User> userTable = new HashMap<>();
    private int id = 0;

    public User join(User user) {
        user.setId(id);
        this.userTable.put(id, user);

        return this.userTable.get(id++);
    }

    public User login(Map<String, String> loginInfo) {
        for (User user : this.userTable.values()) {
            if (isSignedUser(user, loginInfo)){
                return this.userTable.get(user.getId());
            }
        }
        return null;
    }

    private boolean isSignedUser(User user, Map<String, String> loginInfo) {
        return user.getUser_id().equals(loginInfo.get("user_id")) && user.getProfile_info()
            .equals(loginInfo.get("profile_info"));
    }
}
