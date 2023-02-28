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
public class BookReserv {
    private Long id;
    private Long user_id;
    private String bookname; // 책 이름
    private String author; // 책 저자
    private LocalDateTime revdate; // 대출 예상 일자
    private LocalDateTime duedate;
    private int overdue;
    private String status; // 책 예약 상태
}
