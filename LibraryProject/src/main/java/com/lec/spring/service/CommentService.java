package com.lec.spring.service;

import com.lec.spring.domain.Comment;
import com.lec.spring.domain.QryCommentList;
import com.lec.spring.domain.QryResult;
import com.lec.spring.domain.User;
import com.lec.spring.repository.CommentRepository;
import com.lec.spring.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserRepository userRepository;

    @Autowired
    public CommentService(SqlSession sqlSession){
        commentRepository = sqlSession.getMapper(CommentRepository.class); //세션을 꺼내옴
        userRepository = sqlSession.getMapper(UserRepository.class);

        System.out.println("CommentService() 생성");
    }

    //특정 글(id)의 댓글 목록
    public QryCommentList list(Long id) {
        QryCommentList list = new QryCommentList();

        //Db 에서 특정 글의 댓글들을 읽어옴
        List<Comment> comments = commentRepository.findByWrite(id);

        list.setCount(comments.size());//가져온 댓글의 갯수
        list.setList(comments);
        list.setStatus("OK");//정상 처리

        return list;
    }

    //특정글 (writeId)에 특정 사용자 (userId)가 댓글 작성
    public QryResult requestbook(Long requestId, Long userId, String content) {
        User user = userRepository.findById(userId);

        Comment comment =  Comment.builder()
                .user(user)
                .content(content)
                .request_id(requestId)
                .build(); //DB 에 저장

        commentRepository.save(comment);//원래는 리턴값을 받아야함, 실패시 처리도 필요함

        QryResult result = QryResult.builder()
                .count(1)
                .status("OK")
                .build();

        return result;
    }

    //특정 댓글(id) 삭제(post 방식)
    public QryResult delete(Long id) {

        int count = commentRepository.deleteById(id);
        String status = "FAIL";

        if(count>0) status = "OK"; //삭제되면 1 리턴

        QryResult result = QryResult.builder()
                .count(count)
                .status(status)
                .build();
        return result;
    }
}
