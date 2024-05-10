package com.example.shoppingmall.member;

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
}
