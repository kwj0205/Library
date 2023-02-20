package com.lec.spring.service;

import com.lec.spring.domain.Qna;
import com.lec.spring.domain.User;
import com.lec.spring.repository.QnaRepository;
import com.lec.spring.repository.UserRepository;
import com.lec.spring.util.U;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

// Service layer
// - Transaction 담당

@Service
public class BoardService {

    private QnaRepository qnaRepository;
    private UserRepository userRepository;

    @Autowired
    public BoardService(SqlSession sqlSession){  // MyBatis 가 생성한 SqlSession 빈(bean) 객체 주입
        qnaRepository = sqlSession.getMapper(QnaRepository.class);
        userRepository = sqlSession.getMapper(UserRepository.class);
        System.out.println("BoardService() 생성");
    }

    public int qna(Qna qna){
        // 현재 로그인한 작성자 정보
        User user = U.getLoggedUser();

        // 위 정보는 session 의 정보이고, 일단 DB에서 다시 읽어옴
        user = userRepository.findById(user.getId());
        qna.setUser(user);  // 글 작성자 세팅

        int cnt = qnaRepository.save(qna);

        return cnt;
    }

    // 특정 id 의 글 조회
    // 트랜잭션 처리
    // 1. 조회수 증가 (UPDATE)
    // 2. 글 읽어오기 (SELECT)
    @Transactional
    public List<Qna> detail(long id) {
        List<Qna> list = new ArrayList<>();

        Qna qna = qnaRepository.findById(id);

        if(qna != null){
            list.add(qna);
        }

        return list;
    }

    public List<Qna> list(){
        return qnaRepository.findAll();
    }

    // 특정 id 의 글 읽어오기
    // 조회수 증가 없음
    public List<Qna> selectById(long id) {
        List<Qna> list = new ArrayList<>();

        Qna qna = qnaRepository.findById(id);

        if(qna != null){
            list.add(qna);
        }

        return list;
    }

    public int update(Qna qna){
        return qnaRepository.update(qna);
    }

    public int deleteById(long id){
        int result = 0;

        Qna qna = qnaRepository.findById(id);
        if(qna != null) {
            result = qnaRepository.delete(qna);
        }

        return result;
    }

}







