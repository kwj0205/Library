SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS thumbnail;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS bookRent;
DROP TABLE IF EXISTS file;
DROP TABLE IF EXISTS bookRequest;
DROP TABLE IF EXISTS bookReservation;
DROP TABLE IF EXISTS QnA;
DROP TABLE IF EXISTS seatReservation;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS request_comment;

/* Create Tables */

CREATE TABLE book
(
	id int NOT NULL AUTO_INCREMENT,
	rent_id int NOT NULL,
	reserv_id int NOT NULL,
	bookname varchar(100) NOT NULL,
	author varchar(80) NOT NULL,
	publisher varchar(80) NOT NULL,
	bookrelease datetime NOT NULL,
	status enum('대출없음', '예약완료', '대출대기', '대출완료', '연체') NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE bookRent
(
	id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	bookname varchar(100) NOT NULL,
	author varchar(80) NOT NULL,
	rentdate datetime NOT NULL DEFAULT now(),
	returndate datetime NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE bookRequest
(
	id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	req_bookname varchar(100) NOT NULL,
	req_author varchar(80) NOT NULL,
	req_publisher varchar(80),
	req_bookrelease datetime NOT NULL,
	req_regdate datetime NOT NULL DEFAULT now(),
	PRIMARY KEY (id)
);


CREATE TABLE bookReservation
(
	id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	bookname varchar(100) NOT NULL,
	author varchar(80) NOT NULL,
	revdate datetime NOT NULL,
	duedate datetime NOT NULL,
	overdue int(50) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE file
(
	id int NOT NULL AUTO_INCREMENT,
	request_id int NOT NULL,
	source varchar(100) NOT NULL,
	file varchar(100) NOT NULL,
	PRIMARY KEY (id)
);



CREATE TABLE QnA
(
	id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	subject varchar(200) NOT NULL,
	content longtext,
	regdate datetime NOT NULL DEFAULT now(),
	PRIMARY KEY (id)
);


CREATE TABLE seatReservation
(
	id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	seatstatus enum("선택완료", "자리없음", "자리있음") NOT NULL,
	seat_date datetime NOT NULL DEFAULT now(),
	seat_time datetime NOT NULL DEFAULT now(),
	seatnumber varchar(100) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE thumbnail
(
	id int NOT NULL AUTO_INCREMENT,
	thumb_source varchar(100) NOT NULL,
	thumb_file varchar(100) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE user
(
	id int NOT NULL AUTO_INCREMENT,
	username varchar(80) NOT NULL,
	password varchar(100) NOT NULL,
	name varchar(80) NOT NULL,
	phonenumber varchar(50) NOT NULL,
	email varchar(80) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (username),
	UNIQUE (email)
);

CREATE TABLE request_comment
(
	id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	request_id int NOT NULL,
	content text NOT NULL,
	regdate datetime DEFAULT now(),
	PRIMARY KEY (id)
);

/* Create Foreign Keys */

ALTER TABLE thumbnail
	ADD FOREIGN KEY (id)
	REFERENCES book (id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE book
	ADD FOREIGN KEY (rent_id)
	REFERENCES bookRent (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE file
	ADD FOREIGN KEY (request_id)
	REFERENCES bookRequest (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE book
	ADD FOREIGN KEY (reserv_id)
	REFERENCES bookReservation (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE bookRent
	ADD FOREIGN KEY (user_id)
	REFERENCES user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE bookRequest
	ADD FOREIGN KEY (user_id)
	REFERENCES user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE bookReservation
	ADD FOREIGN KEY (user_id)
	REFERENCES user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE QnA
	ADD FOREIGN KEY (user_id)
	REFERENCES user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

ALTER TABLE request_comment
	ADD FOREIGN KEY (user_id)
	REFERENCES user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

ALTER TABLE seatReservation
	ADD FOREIGN KEY (user_id)
	REFERENCES user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

