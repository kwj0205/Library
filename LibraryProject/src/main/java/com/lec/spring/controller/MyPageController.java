package com.lec.spring.controller;

import com.lec.spring.domain.User;
import com.lec.spring.repository.UserRepository;
import com.lec.spring.service.MyPageService;
import com.lec.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/info")
public class MyPageController {

    @Autowired
    private MyPageService mypageService;

    @Autowired
    private UserService userService;

    private UserRepository userRepository;

    public MyPageController(){
        System.out.println("MyPageController() 생성");
    }

    @GetMapping("/mypage")
    public String view(Principal principal, Model model){
        if(principal == null){
            return "user/login";
        }
        String loginId = principal.getName();
        User user = userService.findByUsername(loginId);
        model.addAttribute("user", user);
        return "info/mypage";
    }

    @GetMapping("/reservation")
    public String detail_reservation(Principal principal, Model model){
        if(principal == null){
            return "user/login";
        }
        String loginId = principal.getName();
        User user = userService.findByUsername(loginId);
        System.out.println(user.getId());
        model.addAttribute("list", mypageService.detailreserv(user.getId()));
        return "info/reservation";
    }


    @GetMapping("/rent")
    public String detail_rent(Principal principal, Model model){
        if(principal == null){
            return "user/login";
        }
        String loginId = principal.getName();
        User user = userService.findByUsername(loginId);
        System.out.println(user.getId());
        model.addAttribute("list", mypageService.detailrent(user.getId()));
        return "info/rent";
    }


    @GetMapping("/seatReservation")
    public String detail_seatReservation(Principal principal, Model model){
        if(principal == null){
            return "user/login";
        }
        return "info/seatReservation";
    }

}
