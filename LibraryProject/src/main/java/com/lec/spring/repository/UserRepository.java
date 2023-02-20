package com.lec.spring.repository;

import com.lec.spring.domain.User;

public interface UserRepository {

    // 특정 id (PK) 의 user 리턴
    User findById(Long id);

    // 특정 id (PK) 의 user 리턴
    User findByUsername(String username);

    // 새로운 회원 정보 등록
    int save(User user);

    int update(User user);

}
