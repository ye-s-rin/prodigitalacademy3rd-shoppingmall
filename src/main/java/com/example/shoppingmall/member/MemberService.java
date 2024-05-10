package com.example.shoppingmall.member;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {

    private MemberRepository memberRepository;
}
