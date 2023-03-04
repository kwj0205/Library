package com.lec.spring.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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
    private int status; // 책 대출 상태 (0:대출ㅇ, 1:대출x)

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

}
