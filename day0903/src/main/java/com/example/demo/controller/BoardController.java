package com.example.demo.controller;

import com.example.demo.service.BoardService;
import com.example.demo.vo.Board;
import com.example.demo.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    private int pageSIZE=10;
    private int totalRecord=0;
    private int totalPage=0;


    //pathVariable 을 쓰면 해당 url 을 찾기 때문에 String 으로 뷰 페이지 이름을 반환해줄 필요가 있다.
    @GetMapping("/board/list/{pageNUM}/{id}")
    public String getBoardList(Model m, @PathVariable("pageNUM") int pageNUM, @PathVariable("id") String id){

        System.out.println(pageNUM);
        System.out.println(id);
        // 페이징 처리
//        totalRecord=boardService.getCount();
        if (id.equals("all")){
//            m.addAttribute("list", boardService.findAll(start, end, id));
            totalRecord=boardService.getCount();
        }else{
            totalRecord = boardService.getCount(id);
        }
        totalPage = (int)Math.ceil((double)totalRecord/(double)pageSIZE);
        int start=(pageNUM-1)*pageSIZE+1;
        int end=start+pageSIZE-1;
        if (end>totalRecord){
            end=totalRecord;
        }

        if(id.equals("all")) {
            m.addAttribute("list", boardService.findAll(start, end));
        }else {
            m.addAttribute("list", boardService.findAll(start, end, id));
        }

        List<Board> list =boardService.getBoards(start, end);
        m.addAttribute("boards",list);
        m.addAttribute("totalPage", totalPage);


        return "/board/list";
    }

    @GetMapping("/board/myBoard")
    public String getMyBoard(Model m, HttpSession sesison){
        System.out.println(sesison.getAttribute("member"));
        MemberVO member=(MemberVO)sesison.getAttribute("member");
        String writer=member.getId();
        System.out.println("writer  : "+writer);
        m.addAttribute("myLists",boardService.getListByWriter(writer));
        return "/board/myBoard";
    }
}
