package com.lec.spring.controller;

import ch.qos.logback.classic.Logger;
import com.lec.spring.domain.Book;
import com.lec.spring.domain.BookRent;
import com.lec.spring.domain.BookReserv;
import com.lec.spring.domain.User;
import com.lec.spring.repository.UserRepository;
import com.lec.spring.service.MyPageService;
import com.lec.spring.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/info")
public class MyPageController {

    @Autowired
    private MyPageService mypageService;

    LocalDate datetime =LocalDate.now();

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
        List<BookReserv> detailreserv = mypageService.detailreserv(user.getId());
        model.addAttribute("list", detailreserv);
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

    @PostMapping("/deleteRentOk")
    public String deleteOk(Long id, Model model){
        model.addAttribute("result", mypageService.deleteById(id));
        return "info/deleteRentOk";
    }

    @PostMapping("/rent")
    public String returnDate(Long id, Model model){

        return "info/deleteRentOk";
    }

    @GetMapping("/seatReservation")
    public String detail_seatReservation(Principal principal, Model model){
        if(principal == null){
            return "user/login";
        }
        return "info/seatReservation";
    }

    @GetMapping("/book")
    public void book(){}

    @GetMapping("/intro")
    public void intro(){}

    @GetMapping("/checkout")
    public void checkout(){}


    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String addRent(Principal principal,
                          @RequestParam(value = "bookname")String title,
                          @RequestParam(value = "author")String author) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime twoWeeksAfter = now.plusDays(14);
        String loginId = principal.getName();
        User user = userService.findByUsername(loginId);

        BookRent bookrent = new BookRent();
        bookrent.setUser_id(user.getId());
        bookrent.setBookname(title);
        bookrent.setAuthor(author);
        bookrent.setRentdate(now);
        bookrent.setReturndate(twoWeeksAfter);
        bookrent.setStatus(0);

        mypageService.bookren(bookrent);

        return "redirect:/info/rent";
    }

    @PostMapping("/extendOk")
    public String extend(Long id, Model model){
        mypageService.extendReturn(id);
        return "redirect:/info/rent";
    }

//    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
//    public String addReserv(Principal principal,
//                          Model model,
//                          @RequestParam(value = "bookname")String title,
//                          @RequestParam(value = "author")String author) {
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime twoWeeksAfter = now.plusDays(14);
//        String loginId = principal.getName();
//        User user = userService.findByUsername(loginId);
//
//        BookReserv bookres = new BookReserv();
//        bookres.setUser_id(user.getId());
//        bookres.setBookname(title);
//        bookres.setAuthor(author);
//        bookres.setRevdate(now);
//        bookres.setDuedate(now);
//        bookres.setOverdue(0);
//        bookres.setStatus(0);
//
//        mypageService.bookres(bookres);
//
//        return "redirect:/info/rent";
//    }

//    @PostMapping("/checkout")
//    public String checkout(@Valid Book book
//            , BindingResult result    // <- Validator 가 유효성 검사를 한 결과가 담긴 객체
//            , Model model      // 매개변수 선언시 BindingReult 보다 Model 을 뒤에 두어야 한다.
//            , Principal principal
//            , RedirectAttributes redirectAttrs        // redirect: 시 넘겨줄 값들
//    ){
//
//            String loginId = principal.getName();
//            User user = userService.findByUsername(loginId);
//
//            redirectAttrs.addFlashAttribute("id", user.getId());
////            redirectAttrs.addFlashAttribute("user_id", book.getRent_id());
////            redirectAttrs.addFlashAttribute("bookname", book.getBookname());
////            redirectAttrs.addFlashAttribute("author", book.getAuthor());
////            redirectAttrs.addFlashAttribute("rentdate", datetime);
////            redirectAttrs.addFlashAttribute("returndate", datetime.plusDays(14));
////
////            model.addAttribute("result", mypageService.book(book));
////            model.addAttribute("dto", book);   // auto-generated key
//
//        return "redirect:/info/rent";
//        }


}
