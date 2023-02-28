package com.lec.spring.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class QryCommentList extends  QryResult{// 댓글 목록에 필요한 response

    @JsonProperty("data") //json 변환시 "data"란 이름의 property 로 변환됨 원래는 list
    List<Comment> list; //json 에서 배열로 표현
}
