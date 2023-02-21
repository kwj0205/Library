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













