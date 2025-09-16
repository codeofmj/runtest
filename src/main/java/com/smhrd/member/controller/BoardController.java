package com.smhrd.member.controller;

import com.smhrd.member.dto.Board;
import com.smhrd.member.service.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
public class BoardController {

    @Autowired
    BoardMapper mapper;

    @GetMapping("/list")
    public String list(Model model){
        //1.데이터 수집
        //2.기능 실행
        List<Board> list = mapper.list();

        //Scope: 정보 저장(유지), 4개의 내장객체
        //page: 하나의 JSP 안에서만 유지가능
        //request: 한번의 요청 - 응답 동안만 유지
        //         -> Model 객체: 다이어트한 request, 저장기능 외에 다른 기능을 제거한 request
        //session: 하나의 브라우저 내에서
        //application: 서버종료까지, 공용공간
        model.addAttribute("list", list);

        return "boardMain"; // /WEB-INF/views/boardMain.jsp
    }

    @RequestMapping("/writeBoard")
    public String writeBoard(){
        return "writeBoard"; // /WEB-INF/views/writeBoard.jsp
    }

    // @Value를 이용해서 변수 값 채울 수 있음
    // 어노테이션 안에서 ${프로퍼티 이름}를 사용해서 application.properties에 정의해둔
    // 데이터를 가져올 수 있음.
    @Value("${save.path}")
    private String savePath;

    @PostMapping("/write")
    public String write(Board board, MultipartFile file){

        System.out.println(savePath);

        //1.데이터 수집
        //file을 수집하는 방법
        //(1) 전송된 파일을 수집할 때, MultipartFile 자료형으로 선언
        //(2) 파일을 저장할 폴더 ( 경로에 한글X )
        //(3) 폴더 경로 저장
        //(4) 파일로 변환해서 저장
        if ( file == null ) {
            board.setImg("none");
        } else{

            try{
                //1) 앞에 랜던함 문자열을 붙여서, 파일 이름 중복 방지
                String uuid = UUID.randomUUID().toString();

                String filename = uuid + file.getOriginalFilename();

                //2) 전체 경로 ( 폴더 경로 + 파일 이름 )
                Path path = Paths.get(savePath + filename );


                System.out.println(path);

                //3) 저장
                file.transferTo( path );

                board.setImg(filename);

            }catch (Exception e){
                e.printStackTrace();
            }

        }

        //2.기능 실행
        mapper.write(board);

        //3.View 이동
        return "redirect:/list";
    }

    @GetMapping("/view")
    public String view(int idx, Model model){

        //1.데이터 수집
        //2.기능 실행
        Board board = mapper.view(idx);

        model.addAttribute("board",board);

        //3.View 이동
        return "viewBoard"; // /WEB-INF/views/viewBoard.jsp
    }
    
    // ~~~/delete/3
    @GetMapping("/delete/{idx}")
    public String delete(@PathVariable("idx") int idx){
        
        //1.데이터 수집
        //2.기능 실행
        mapper.delete(idx);
        
        //3.View 이동
        return "redirect:/list";
    }

}
