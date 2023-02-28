package com.lec.spring.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    private Long id;

    @ToString.Exclude //Tostring 에서 배제
    private User user;//댓글 작성자(FK)

    @JsonIgnore //Json 변환시 제외하는 필드
    private Long request_id; // 어느글의 댓글(FK)

    private String content; //댓글 내용

    //java.time.* 객체 변환을 위한 annotation -> 자바 객체를 json 으로 변환하는 것은 Jackson Databind 인데 local이 뒤늦게 등장했기 때문에..
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul") //시간 폼 설정
    @JsonProperty("regdate")
    private LocalDateTime regDate; //댓글 작성 시간


}