package com.lec.spring.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestBook {
    private Long id;    // 글 id (PK)
    private String req_bookname;
    private String req_author;
    private String req_publisher;
    private LocalDateTime req_bookrelease;
    private LocalDateTime req_regDate;

    private User user;      // 글 작성자 (FK)

    //첨부파일, 댓글
    @ToString.Exclude //tostring 값 제외
    @Builder.Default //아래와 같이 초깃값이 있으면  Builder.Default처리 (builder 제공 안함)
    private List<FileDTO> fileList = new ArrayList<>();
}
