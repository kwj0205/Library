package com.lec.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/library")
public class LibraryController {

    public LibraryController(){
        System.out.println("LibraryController() 생성");
    }

    @GetMapping("/intro")
    public void intro(){}

    @GetMapping("/book")
    public void book(){}

    @GetMapping("checkout")
    public void checkout(){}

//    @PostMapping("/book")
//    public String bookOk(RedirectAttributes redirectAttributes){
//        redirectAttributes.addFlashAttribute("title", Book)
//
//    }

}
