package com.lec.spring.controller;

import com.lec.spring.domain.Comment;
import com.lec.spring.domain.QryCommentList;
import com.lec.spring.domain.QryResult;
import com.lec.spring.domain.User;
import com.lec.spring.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController // data 를 response 한다
@RequestMapping("/comment")
public class CommentController {
    @Autowired //생성자 자동주입
    private CommentService commentService;

    public CommentController() {
        System.out.println(getClass().getName() + "() 생성");
    }

    @GetMapping("/test1")
    public QryCommentList test1(Long id){// 특정글의 댓글 목록
        // JAva 객체 -> Json 의 Object 객체로 변환
        QryCommentList list = new QryCommentList();

        list.setCount(1);
        list.setStatus("OK");
        Comment cmt = Comment.builder()
                .user(User.builder().username("한가위").id(34L).regDate(LocalDateTime.now()).name("대보름").build()) //필요한 필드만 세팅하기위해서 build 사용
                .content("정말 재밋어요")
                .regDate(LocalDateTime.now())
                .build();
        List<Comment> cmtList = new ArrayList<>();
        cmtList.add(cmt);
        list.setList(cmtList);

        return list; //Json 바뀌어서 response 왜? RestController 때문에
    }

    @GetMapping("/test2")
    public List<Integer> test2(){
        // Java 의 list, 배열을 리턴하면 Json 에서는 배열로 리턴
        List<Integer> list  = Arrays.asList(10,20,30); //list 생성
        return list;
    }

    @GetMapping("/test3")
    public Map<Integer,String> test3(){
        // double-brace 구문. {{ }} 이렇게 만들어서 map을 초기화
        Map<Integer,String > myMap= new HashMap<>(){{
            put(100,"백이다");
            put(200,"이백이다");
        }};
        return myMap;
    }

    @GetMapping("/list")
    public QryCommentList list(Long id){
        return commentService.list(id);
    }

    //Model 객체 필요 없음 mvc에서 v에사용하는데 여기서는 view를 리턴하는 것이 아니라 data를 리턴하는 것이여서 필요하지 않음
    @PostMapping("/rebook")
    //글작성에는 어느글에 누가 했는지 보내줌
    public QryResult write(
            @RequestParam("request_id") Long requestId,
            @RequestParam("user_id")Long userId, //변수와 넘어오는 값이 다른 경우 사용
            String content
    )
    {
        return commentService.requestbook(requestId,userId,content);
    }

    @PostMapping("/delete")
    public QryResult delete(Long id){
        return commentService.delete(id);
    }
}
