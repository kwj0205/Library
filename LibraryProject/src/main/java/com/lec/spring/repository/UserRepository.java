package com.lec.spring.repository;

import com.lec.spring.domain.User;

import java.util.List;

public interface UserRepository {

    // 특정 id (PK) 의 user 리턴
    // 특정 회원 정보 가져오기
    User findById(Long id);

    // 특정 id (PK) 의 user 리턴
    User findByUsername(String username);

    // 새로운 회원 정보 등록
    int save(User user);

    int update(User user);

    // 특정 회원 정보 수정
    int userUpdate(User user);

    // 비밀번호 변경
    int pwUpdate(User user);

}
