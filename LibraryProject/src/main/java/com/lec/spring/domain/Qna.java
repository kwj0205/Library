package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// Model 객체 (domain, DTO: Data Transfer Object)

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Qna {
    private Long id;    // 글 id (PK)
    private String subject;
    private String content;
    private LocalDateTime regDate;

    private User user;      // 글 작성자 (FK)
}
