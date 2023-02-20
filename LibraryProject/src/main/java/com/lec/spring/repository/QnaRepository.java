package com.lec.spring.repository;

// Repository layer
// DataSource (DB) 등에 대한 직접적인 접근

import com.lec.spring.domain.Qna;

import java.util.List;


public interface QnaRepository {

    // 새글 작성 <- Write
    int save(Qna qna);

    // 특정 id 글 내용 읽기
    Qna findById(Long id);

    // 전체 글 목록 : 최신순
    List<Qna> findAll();

    // 특정 id 글 수정 (제목, 내용)
    int update(Qna qna);

    // 특정 id 글 삭제하기
    int delete(Qna qna);

}













