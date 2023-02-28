package com.lec.spring.repository;

import com.lec.spring.domain.Book;
import com.lec.spring.domain.BookRent;
import com.lec.spring.domain.BookReserv;

import java.util.List;

public interface BookRepository {
    Book findById(Long rent_id);
    List<BookRent> findByUserIdRent(Long user_id);
    List<BookReserv> findByUserIdReserv(Long user_id);
    List<Book> findAll();
    List<BookRent> findAllRent();
    List<BookReserv> findAllReserv();
}
