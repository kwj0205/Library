package com.lec.spring.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class MenuController {
    public MenuController(){
        System.out.println(getClass().getName() + "() 생성");
    }

    // /home
    @RequestMapping("/menu")
    public void menu() {}

    @RequestMapping("/auth")
    @ResponseBody
    public Authentication auth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
