package com.lec.spring.repository;

import com.lec.spring.domain.Comment;

import java.util.List;

public interface CommentRepository {
    //특정 글(write_id) 의 댓글 목록
    List<Comment> findByWrite(Long request_id); // id를 받아서 목록을 리턴

    // 댓글 작성 <--- Comment 객체로 부터 받음
    int save(Comment comment);

    //특정 댓글(id) 삭제
    int deleteById(Long id);

}