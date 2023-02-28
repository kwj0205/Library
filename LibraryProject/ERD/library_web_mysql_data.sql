DELETE FROM file;
ALTER TABLE file AUTO_INCREMENT = 1;
DELETE FROM thumbnail;
ALTER TABLE thumbnail AUTO_INCREMENT = 1;
DELETE FROM QnA;
ALTER TABLE QnA AUTO_INCREMENT = 1;
DELETE FROM bookRequest;
ALTER TABLE bookRequest AUTO_INCREMENT = 1;
DELETE FROM bookRent ;
ALTER TABLE bookRent AUTO_INCREMENT = 1;
DELETE FROM bookReservation;
ALTER TABLE bookReservation AUTO_INCREMENT = 1;
DELETE FROM seatReservation;
ALTER TABLE seatReservation AUTO_INCREMENT = 1;
DELETE FROM `user`;
ALTER TABLE `user` AUTO_INCREMENT = 1;

-- 샘플 사용자
INSERT INTO `user` (username, password, name, phonenumber, email) VALUES
-- ('USER1', '1234', '홍길동', '010-1111-2222', 'aaaa@bbb.com'),
-- ('USER2', '1234', '김철수', '010-2222-3333', 'cccc@ddd.com'),
-- ('USER3', '1234', '김나나', '010-5466-8888', 'avcd@eee.com')
('USER1', '$2a$10$6gVaMy7.lbezp8bGRlV2fOArmA3WAk2EHxSKxncnzs28/m3DXPyA2', '홍길동', '010-1111-2222', 'aaaa@bbb.com'),
('USER2', '$2a$10$7LTnvLaczZbEL0gabgqgfezQPr.xOtTab2NAF/Yt4FrvTSi0Y29Xa', '김철수', '010-2222-3333', 'cccc@ddd.com'),
('USER3', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '김나나', '010-5466-8888', 'avcd@eee.com')
;

-- 샘플 Q&A 게시판 내용
INSERT INTO QnA (user_id, subject, content) VALUES
(1, '제목입니다1', '내용입니다1'),
(2, '제목입니다2', '내용입니다2'),
(3, '제목입니다3', '내용입니다3')
;


INSERT INTO bookrequest (user_id, req_bookname, req_author,req_publisher,req_bookrelease) VALUES
(1, '제목입니다1', '저자입니다1','출판사입니다1','2000-05-03'),
(2, '제목입니다2', '저자입니다2','출판사입니다2','2001-03-04'),
(3, '제목입니다3', '저자입니다3','출판사입니다3','1998-06-23')
;

INSERT INTO file(request_id, source, file) VALUES
(1, 'face01.png', 'face01.png'),
(1, 'face02.png', 'face02.png'),
(2, 'face03.png', 'face03.png'),
(2, 'face04.png', 'face04.png'),
(3, 'face05.png', 'face05.png'),
(3, 'face06.png', 'face06.png')

;

INSERT INTO request_comment(user_id, request_id, content) VALUES
(1, 1, '1. user1이 1번글에 댓글 작성.'),
(1, 1, '2. user1이 1번글에 댓글 작성.'),
(1, 2, '3. user1이 2번글에 댓글 작성.'),
(1, 2, '4. user1이 2번글에 댓글 작성.'),
(1, 3, '5. user1이 3번글에 댓글 작성.'),
(1, 3, '6. user1이 3번글에 댓글 작성.'),
(1, 4, '7. user1이 4번글에 댓글 작성.'),
(1, 4, '8. user1이 4번글에 댓글 작성.'),
(3, 1, '9. admin1이 1번글에 댓글 작성.'),
(3, 1, '10. admin1이 1번글에 댓글 작성.'),
(3, 2, '11. admin1이 2번글에 댓글 작성.'),
(3, 2, '12. admin1이 2번글에 댓글 작성.'),
(3, 3, '13. admin1이 3번글에 댓글 작성.'),
(3, 3, '14. admin1이 3번글에 댓글 작성.'),
(3, 4, '15. admin1이 4번글에 댓글 작성.'),
(3, 4, '16. admin1이 4번글에 댓글 작성.')
;












