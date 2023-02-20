package com.lec.spring.controller;

import com.lec.spring.domain.Qna;
import com.lec.spring.domain.QnaValidator;
import com.lec.spring.service.BoardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

// Controller layer
//  request 처리 ~ response
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    public BoardController(){
        System.out.println("BoardController() 생성");
    }

    @GetMapping("/qna")
    public void qna(){}

    @PostMapping("/qna")
    public String qnaOk(
            @ModelAttribute("dto") Qna qna
            , Model model      // 매개변수 선언시 BindingReult 보다 Model 을 뒤에 두어야 한다.
        ){
        model.addAttribute("result", boardService.qna(qna));
        return "board/qnaOk";
    }

    @GetMapping("/detail")
    public void detail(Long id, Model model){
        model.addAttribute("list", boardService.detail(id));
    }

    @GetMapping("/list")
    public void list(Model model){
        model.addAttribute("list", boardService.list());
    }

    @GetMapping("/update")
    public void update(Long id, Model model){
        model.addAttribute("list", boardService.selectById(id));
    }

    @PostMapping("/update")
    public String updateOk(@ModelAttribute("dto") Qna qna, Model model){
        model.addAttribute("result", boardService.update(qna));
        return "board/updateOk";
    }

    @PostMapping("/delete")
    public String deleteOk(Long id, Model model){
        model.addAttribute("result", boardService.deleteById(id));
        return "board/deleteOk";
    }

    // 이 컨트롤러 클래스의 handler 에서 폼 데이터를 바인딩 할때 검증하는 Validator 객체 지정
    @InitBinder
    public void initBinder(WebDataBinder binder){
        System.out.println("initBinder() 호출");
        binder.setValidator(new QnaValidator());
    }

}










