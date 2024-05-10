package com.example.shoppingmall.member;

import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class MemberService {

    private MemberRepository memberRepository;

    public Member signup(@RequestBody Member member){
        return this.memberRepository.signup(member);
    }

    public Member login(Map<String, String> loginInfo) {
        return this.memberRepository.login(loginInfo);
    }
}
