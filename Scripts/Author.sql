USE bookshop;
INSERT INTO `bookshop`.`Author`
(`f_name`,
`l_name`,
`m_name`)
VALUES
('Brian','Kernighan', 'Wilson'),
('Dennis','Ritchie','MacAlistair'),
('Suzanne', 'Collins',NULL),
('Harper', 'Lee',NULL),
('Neil', 'Gaiman',NULL),
('Yann', 'Martel',NULL),
('Joanne', 'Rowling',NULL),
('James', 'Barrie','Matthew'),
('Kathryn', 'Stockett',NULL),
('John', 'Green',NULL);

select * from Author;
