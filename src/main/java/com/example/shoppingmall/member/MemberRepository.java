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
}
