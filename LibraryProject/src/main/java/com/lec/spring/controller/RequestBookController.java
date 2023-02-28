package com.lec.spring.controller;


import com.lec.spring.domain.RequestBook;
import com.lec.spring.domain.RequestBookValidator;
import com.lec.spring.service.RequestBookService;
import com.lec.spring.util.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@RequestMapping("/requestbook")
public class RequestBookController {

    @Autowired
    private RequestBookService requestBookService;

    public RequestBookController(){
        System.out.println("RequestBookController() 생성");
    }
    @GetMapping("/rebook")
    public void requestbook(){}

    @PostMapping("/rebook")
    public String rebookOk(
            @RequestParam Map<String , MultipartFile> files // 첨부파일들
            ,@ModelAttribute("dto") RequestBook requestBook
            , Model model      // 매개변수 선언시 BindingReult 보다 Model 을 뒤에 두어야 한다.
    ){
        model.addAttribute("result", requestBookService.requestbook(requestBook,files));
        return "requestbook/rebookOk";
    }

    @GetMapping("/detail")
    public void detail(Long id, Model model){
        model.addAttribute("list", requestBookService.detail(id));
        model.addAttribute("conPath", U.getRequest().getContextPath()); //여기서 conPath 값을 넘겨줌
    }

    @GetMapping("/list")
    public void list(Model model){
        model.addAttribute("list", requestBookService.list());

    }

    @GetMapping("/update")
    public void update(long id, Model model){
        model.addAttribute("list", requestBookService.selectById(id));
    }

    @PostMapping("/update")
    public String updateOk
            (@ModelAttribute("dto") RequestBook requestBook
                    ,@RequestParam Map<String,MultipartFile> files //새로 추가될 첨부파일들
                    ,Long[] delfile //삭제될 파일들
                    , Model model){
        model.addAttribute("result", requestBookService.update(requestBook,files,delfile));//받아온 것들을 서비스에서 처리
        return "requestbook/updateOk";
    }

    @PostMapping("/delete")
    public String deleteOk(Long id, Model model){
        model.addAttribute("result", requestBookService.deleteById(id));
        return "requestbook/deleteOk";
    }

    // 이 컨트롤러 클래스의 handler 에서 폼 데이터를 바인딩 할때 검증하는 Validator 객체 지정
    @InitBinder
    public void initBinder(WebDataBinder binder){
        System.out.println("initBinder() 호출");
        binder.setValidator(new RequestBookValidator());
    }

}
