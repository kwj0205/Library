package com.lec.spring.service;

import com.lec.spring.domain.BookRent;
import com.lec.spring.domain.Book;
import com.lec.spring.domain.BookReserv;
import com.lec.spring.repository.BookRepository;
import com.lec.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MyPageService {
    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    @Autowired
    public MyPageService(SqlSession sqlSession){  // MyBatis 가 생성한 SqlSession 빈(bean) 객체 주입
        userRepository = sqlSession.getMapper(UserRepository.class);
        bookRepository = sqlSession.getMapper(BookRepository.class);
        System.out.println("MyPageService() 생성");
    }


    public List<BookReserv> detailreserv(Long user_id){
//      List<BookReserv> list = new ArrayList<>();
        List<BookReserv> list = bookRepository.findByUserIdReserv(user_id);
        // -> 책 예약 내역 한가지만 있는 것이 아니니까 List로 받아와야 함

        return list;
    }


    public List<BookRent> detailrent(Long user_id){
//      List<BookRent> list = new ArrayList<>();
        List<BookRent> list = bookRepository.findByUserIdRent(user_id);
        // -> 책 대출 내역 한가지만 있는 것이 아니니까 List로 받아와야 함

//        if(bookrent != null){
//            list.add(bookrent);
//        }

        return list;
    }

    public int bookres(Book book){
        return bookRepository.saveres(book);
    }

    public int bookren(Book book){
        return bookRepository.saveren(book);
    }

    public int book(Book book){
        return bookRepository.save(book);
    }


}
