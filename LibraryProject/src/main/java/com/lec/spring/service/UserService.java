package com.lec.spring.service;

import com.lec.spring.domain.User;
import com.lec.spring.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    @Autowired
    public UserService(SqlSession sqlSession){
        userRepository = sqlSession.getMapper(UserRepository.class);
        System.out.println(getClass().getName() + "() 생성");
    }

    // username(회원 아이디) 의 User 정보 읽어오기
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    // 특정 username(회원 아이디) 의 회원이 존재하는지 확인
    public boolean isExist(String username){
        User user = findByUsername(username);
        return (user != null) ? true : false;
    }

    // 신규 회원 등록
    public int register(User user){
        user.setUsername(user.getUsername().toUpperCase());     // DB 에는 회원아이디(username) 을 대문자로 저장
        user.setPassword(passwordEncoder.encode(user.getPassword()));   // password 는 암호화 해서 저장해야 한다.
        userRepository.save(user);      // 새로 회원(User) 저장. id 값 받아옴

        return 1;
    }

    // 회원정보 수정

    public List<User> selectById(Long id) {
        List<User> userInfo = new ArrayList<>();
        User user = userRepository.findById(id);

        if(user != null){
            userInfo.add(user);
        }

        return userInfo;
    }

    public int userUpdate(User user){
        return userRepository.userUpdate(user);
    }



}
