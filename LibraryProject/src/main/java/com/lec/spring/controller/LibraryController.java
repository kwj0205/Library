package com.lec.spring.controller;

import com.lec.spring.domain.User;
import com.lec.spring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private BookService bookService;
    private UserService userService;

    public LibraryController(){
        System.out.println("LibraryController() 생성");
    }

    @GetMapping("/intro")
    public void intro(){}

    @GetMapping("/book")
    public void book(){}

    @GetMapping("checkout")
    public void checkout(){}

    @PostMapping("/checkout")
    public String writeOk(@Valid Book book
            , BindingResult result
            , Principal principal
            , Model model
            , RedirectAttributes redirectAttrs
    ){

        String loginId = principal.getName();
        User user = userService.findByUsername(loginId);
        System.out.println(user.getId());
        // validation 에러가 있었다면 redirect 한다!
        if(result.hasErrors()){
            // redirect 시, 기존에 입력했던 값들은 보이게 하기
            redirectAttrs.addFlashAttribute("rent_id", user.getId());
            redirectAttrs.addFlashAttribute("bookname", book.getBookname());
            redirectAttrs.addFlashAttribute("author", book.getAuthor());
            redirectAttrs.addFlashAttribute("publisher", book.getPublisher());
            redirectAttrs.addFlashAttribute("bookrelease", book.getBookrelease());

            List<FieldError> errList = result.getFieldErrors();
            for(FieldError err : errList) {
                // addFlashAttribute() <- post 방식으로 redirect 발생될때
                redirectAttrs.addFlashAttribute("error", err.getCode());
                break;
            }

            return "redirect:/library/checkout";
        }

        model.addAttribute("result", bookService.book(book));
        model.addAttribute("dto", book);   // auto-generated key
        return "library/checkoutOk";
    }

}
