package com.lec.spring.repository;

import com.lec.spring.domain.User;
import com.lec.spring.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private SqlSession sqlSession;

    User user;

    @Test
    void userTest1(){
        UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
        System.out.println(userRepository.userUpdate(user));

        User user = User.builder()
                .id(4L)
                .username("user4")
                .name("김나나")
                .password("$2a$10$Mz6NfC9oamrjc3JTSuzf4u5qJ5096d3kk7Z2TBOWbKHYBJ0KVbVkq")
                .email("nnn@ccc.com")
                .phonenumber("010-6666-8088")
                .build();
        userRepository.userUpdate(user);
        System.out.println(userRepository.findById(4L));

        System.out.println("테스트 완료");
    }

}