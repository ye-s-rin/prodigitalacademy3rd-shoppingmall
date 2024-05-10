package com.example.shoppingmall.member;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class MemberController {

    private MemberService memberService;

    @PostMapping(value = "/signup")
    public ResponseEntity signup(@RequestBody Member member){
        Member signedMember = this.memberService.signup(member);

        if(signedMember != null) {
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody Map<String, String> loginInfo){
        Member loginedMember = this.memberService.login(loginInfo);

        if(loginedMember != null){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
