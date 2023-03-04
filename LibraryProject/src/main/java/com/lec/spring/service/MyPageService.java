package com.lec.spring.service;

import com.lec.spring.domain.*;
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
        List<BookReserv> list = bookRepository.findByUserIdReserv(user_id);
        // -> 책 예약 내역 한가지만 있는 것이 아니니까 List로 받아와야 함

        return list;
    }


    public List<BookRent> detailrent(Long user_id){
        List<BookRent> list = bookRepository.findByUserIdRent(user_id);
        // -> 책 대출 내역 한가지만 있는 것이 아니니까 List로 받아와야 함

//        if(bookrent != null){
//            list.add(bookrent);
//        }

        return list;
    }

    public int bookres(BookReserv bookReserv){
        return bookRepository.saveres(bookReserv);
    }

    public int bookren(BookRent bookRent){
        return bookRepository.saveren(bookRent);
    }

    public int book(Book book){
        return bookRepository.save(book);
    }

    public int deleteByIdRent(long id){
        int result = 0;

        List<BookRent> list = bookRepository.findByIdRent(id);
        if(list != null) {
            result = bookRepository.deleteRent(list.get(0));
        }

        return result;
    }

    public int extendReturn(Long id){
        int result = bookRepository.extendReturn(id);
        return result;
    }

    public int deleteByIdReserv(long id){
        int result = 0;

        List<BookReserv> list = bookRepository.findByIdReserv(id);
        if(list != null) {
            result = bookRepository.deleteReserv(list.get(0));
        }

        return result;
    }

}
