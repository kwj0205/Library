package com.lec.spring.controller;

import com.lec.spring.domain.User;
import com.lec.spring.domain.UserValidator;
import com.lec.spring.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserController() {
        System.out.println(getClass().getName() + "() 생성");
    }

    @GetMapping("/login")
    public void login(){
    }

    // onAuthenticationFailure 에서 로그인 실패시 forwoarding 용
    // request 에 담겨진 attribute 는 ThymeLeaf 에서 그대로 표현 가능

    @PostMapping("/loginError")
    public String loginError(){
        return "user/login";
    }

    @GetMapping("/register")
    public void register(){}

    @PostMapping("/register")
    public String registerOk(@Valid User user
            , BindingResult result  // UserValidator 가 유효성 검증한 결과가 담긴 객체
            , Model model
            , RedirectAttributes redirectAttrs
    ){
        // 이미 등록된 중복된 아이디(username) 이 들어오면
        if(!result.hasFieldErrors("username") && userService.isExist(user.getUsername())){
            result.rejectValue("username", "이미 존재하는 아이디입니다");
        }

        // 검증 에러가 있었다면 redirect 한다
        if(result.hasErrors()){
            redirectAttrs.addFlashAttribute("username", user.getUsername());
            redirectAttrs.addFlashAttribute("name", user.getName());

            List<FieldError> errList = result.getFieldErrors();
            for(FieldError err : errList){
                redirectAttrs.addFlashAttribute("error", err.getCode());    // 가장 처음에 발견된 에러를 담아 보냄
                break;
            }

            return "redirect:/user/register";
        }

        // 에러 없었으면 회원 등록 진행
        String page = "/user/registerOk";
        int cnt = userService.register(user);
        model.addAttribute("result", cnt);
        return page;
    }

    // 회원 정보 수정

    @GetMapping("/userUpdate")
    public void userUpdate(Long id, Model model){
        model.addAttribute("userInfo", userService.selectById(id));
    }

    @PostMapping("/userUpdate")
    public String userUpdateOk(User user, Model model){
        model.addAttribute("result", userService.userUpdate(user));
        model.addAttribute("dto", user);
        return "user/userUpdateOk";
    }

    @GetMapping("/pwUpdate")
    public void pwUpdate(Long id, Model model) {
        model.addAttribute("userPw", userService.selectById(id));
    }

    @PostMapping("/pwUpdate")
    public String pwUpdate(User user, HttpSession session, Model model) {
        model.addAttribute("result", userService.pwUpdate(user));
        model.addAttribute("dto", user);

        return "user/pwUpdateOk";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setValidator(new UserValidator());
    }


}
