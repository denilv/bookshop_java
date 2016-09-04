use bookshop;
INSERT INTO `bookshop`.`book_to_author`
(`book_id`,
`author_id`)
VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 4),
(4, 5),
(5, 6),
(6, 7),
(7, 8),
(8, 7),
(9, 8),
(10, 9);

select * from book_to_author;
