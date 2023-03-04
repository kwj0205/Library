package com.lec.spring.repository;

import com.lec.spring.domain.Book;
import com.lec.spring.domain.BookRent;
import com.lec.spring.domain.BookReserv;
import com.lec.spring.domain.RequestBook;

import java.util.List;

public interface BookRepository {
    Book findById(Long rent_id);

    List<BookRent> findByUserIdRent(Long user_id);
    List<BookRent> findByIdRent(Long id);
    List<BookReserv> findByIdReserv(Long id);
    List<BookReserv> findByUserIdReserv(Long user_id);

    List<Book> findAll();
    List<BookRent> findAllRent();
    List<BookReserv> findAllReserv();

    int save(Book book);

    int saveren(BookRent bookRent);

    int saveres(BookReserv bookReserv);

    int deleteRent(BookRent bookRent);

    int deleteReserv(BookReserv bookReserv);

    int extendReturn(Long id);
}
