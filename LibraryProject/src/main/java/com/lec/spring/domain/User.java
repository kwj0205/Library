package com.lec.spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;

    private String username;    // 회원 아이디
    @JsonIgnore
    private String password;    // 회원 비밀번호

    @ToString.Exclude
    @JsonIgnore
    private String re_password; // 비밀번호 확인

    private String name;        // 회원 이름
    private String phonenumber; // 회원 전화번호
    private String email;       // 회원 이메일
}
