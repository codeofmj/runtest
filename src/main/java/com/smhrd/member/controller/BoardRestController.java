package com.smhrd.member.controller;

import com.smhrd.member.dto.Board;
import com.smhrd.member.service.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardRestController {

    @Autowired
    private BoardMapper mapper;

    @GetMapping("search")
    public List<Board> search(String text){
        //1.데이터 수집
        //2.기능 실행
        System.out.println("request:" + text);

        List<Board> list = mapper.search("%" + text + "%");

        //3.데이터 응답
        return list;
    }

    @GetMapping("chart")
    public List<Board> chart(){
        //1.데이터 수집
        //2.기능 실행
        List<Board> list = mapper.chart();

        //3.데이터 응답
        return list;
    }
}
