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
public class Book {
    private String bookname; // 책 이름
    private String author; // 책 저자
    private String publisher; // 책 출판사
    private LocalDateTime bookrelease; // 책 출시일
    private Long id;
    private Long rent_id; // 대출자
    private Long reserve_id; // 예약자
    private String status; // 책 예약,대출 상태
}
