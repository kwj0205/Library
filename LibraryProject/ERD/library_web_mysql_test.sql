SHOW TABLES;

SELECT TABLE_NAME FROM information_schema.TABLES
WHERE TABLE_SCHEMA = 'library';

SELECT * FROM `user` ORDER BY username DESC;

SELECT * FROM QnA ORDER BY id DESC;
SELECT * FROM book;
SELECT * FROM bookRent ORDER BY id DESC;
SELECT * FROM bookReservation ORDER BY id DESC;

SELECT * FROM bookrequest  ORDER BY id DESC;
SELECT * FROM file  ORDER BY id DESC;

SELECT * FROM request_comment  ORDER BY id DESC;


UPDATE bookRent SET returnDate = DATE_ADD(returndate, INTERVAL 14 DAY) WHERE id = 6;


