package com.lec.spring.repository;


import com.lec.spring.domain.RequestBook;

import java.util.List;

public interface RequestBookRepository {
    // 새글 작성 <- Write
    int save(RequestBook requestBook);

    // 특정 id 글 내용 읽기
    RequestBook findById(Long id);

    // 전체 글 목록 : 최신순
    List<RequestBook> findAll();

    // 특정 id 글 수정 (제목, 내용)
    int update(RequestBook requestBook);

    // 특정 id 글 삭제하기
    int delete(RequestBook requestBook);
}
