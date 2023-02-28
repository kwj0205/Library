package com.lec.spring.repository;

import com.lec.spring.domain.FileDTO;

import java.util.List;
import java.util.Map;

public interface FileRepository {//임의이 개수의 첨부파일 등록할 수 있음
    /**
     * 특정 글(writeId) 에 첨부파일(들) 추가 INSERT
     * 글 insert, update 시 사용됨.
     * @param list :  Map<String, Object> 들의 List
     *                      ↓        ↓
     *                   <"source",원본파일명>
     *                   <"file", 저장된파일명>
     * @param requestId : 첨부될 글
     * @return : DML 수행 결과값
     */

    int insert(List<Map<String,Object>> list, Long requestId); // 리스트는 원본파일명, 실제로 저장된 파일명이 들어와야함 // 첨부된 글

    int save(FileDTO file);

    //특정 글 (writeId)의 첨부파일들
    List<FileDTO> findByWrite(Long requestId);

    //특정 첨부파일(id) 한개 select
    FileDTO findById(Long id);

    //선택된 첨부파일들 select
    //글 수정에서 사용
    List<FileDTO> findByIds(Long [] ids);

    //선택된 첨부파일들 delete
    //글 수정에서 사용
    int deleteByIds(Long [] ids);

    //특정 첨부 파일을  DB 삭제
    int delete(FileDTO file);
}
