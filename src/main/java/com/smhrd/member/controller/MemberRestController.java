package com.smhrd.member.controller;

import com.smhrd.member.dto.Member;
import com.smhrd.member.service.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberRestController {

    @Autowired
    private MemberMapper mapper;

    @ResponseBody
    @GetMapping("/check")
    public String check(String email){
        //1.데이터 수집
        //2.기능 실행
        Member member = mapper.check(email);

        //3.데이터 응답
        if(member == null){
            return "t";
        }else{
            return "f";
        }
    }

}
