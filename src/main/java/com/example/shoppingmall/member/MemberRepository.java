package com.example.shoppingmall.member;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    private Map<Integer, Member> memberTable = new HashMap<>();
    private int id = 0;

    public Member signup(Member member) {
        member.setId(id);
        this.memberTable.put(id, member);

        return this.memberTable.get(id++);
    }

    public Member login(Map<String, String> loginInfo) {
        for (Member member : this.memberTable.values()) {
            if (isSignedMember(member, loginInfo)){
                return this.memberTable.get(member.getId());
            }
        }
        return null;
    }

    private boolean isSignedMember(Member member, Map<String, String> loginInfo) {
        return member.getUser_id().equals(loginInfo.get("user_id")) && member.getProfile_info()
            .equals(loginInfo.get("profile_info"));
    }
}
