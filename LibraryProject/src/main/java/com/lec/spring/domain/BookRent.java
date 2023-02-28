package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRent {
    private Long id;
    private Long user_id;
    private String bookname; // 책 이름
    private String author; // 책 저자
    private LocalDateTime returndate; // 반납일
    private LocalDateTime rentdate; // 대출일
    private String status; // 책 대출 상태
}
