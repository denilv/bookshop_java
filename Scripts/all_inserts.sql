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

INSERT INTO `bookshop`.`Customer`
(`f_name`,
`l_name`,
`m_name`,
`admin`,
`email`,
`password`,
`contact_number`,
`address`)
VALUES
('admin','admin','admin',1,'admin','admin','admin','Moscow'),
('Ilia','Denisov','Vyacheslavovich',1,'denilv@mail.ru','qwerty','+79261710614','Moscow'),
('Vladimir','Lopatkin','Vladimirovich',0,'qwerty@ya.ru','qwerty','+79261310619','Volgograd'),
('Nikolay','Kozlov','Sergeevich',0,'qwerty@rambler.ru','qwerty','+79261710615','Vorkuta'),
('Varphalameev','Denis','Ilich',0,'qwerty@gmail.com','qwerty','+79261710615','Surgut');

insert into Genre(name)
values 
('Education')
,('Triller')
,('Novel')
,('Detective')
,('Drama');

insert into Publ_house(name)
values ('Aist'), ('Nika'), ('Metro'), ('Alpha-book'), ('AST');

INSERT INTO `bookshop`.`Shipping_method`
(`name`,
`price`)
VALUES
('Pickup',0),
('Express delivery', 500);

INSERT INTO `bookshop`.`Book`
(id,
`ISBN`,
`title`,
`price`,
`publ_house_id`,
`publication_date`,
`pages`,
`available_amount`,
`genre_id`
)
VALUES
(1,'1001234561','The C Programming Language', 1000,1,'1999-09-14',374,10,1),
(2,'1001234562','The Hunger Games', 1000,2,'2010-1-23',128,20,2),
(3,'1001234563','To Kill a Mockingbird', 250,3,'2013-3-12',256,30,3),
(4,'1001234564','Coraline', 700,4,'2008-5-11',512,40,4),
(5,'1001234565','Life of Pi', 600,5,'2007-6-1',1024,50,2),
(6,'1001234566','Harry Potter and the Half-Blood Prince', 800,1,'1996-4-2',312,60,4),
(7,'1001234567','Peter Pan', 900,1,'2003-3-23',228,70,3),
(8,'1001234568','James Potter and the Hall of Elders\' Crossing', 100,2,'2004-2-27',322,80,2),
(9,'1001234569','Paper Towns', 870,4,'2005-09-9',1337,90,4),
(10,'1001234510','The Help', 300,5,'2006-1-10',282,100,3);

INSERT INTO `bookshop`.`Orders`
(`customer_id`,
`status`,
`date_time`,
`shipping_method`,
`comment`,
`shipping_address`)
VALUES
(1,'Preparation','2016-1-1',1,NULL,'Moscow'),
(3,'Shipped','2016-2-2',2,NULL,'Surgut'),
(4,'Delivered','2016-3-3',1,'Remove receipts, pls.','Moscow');

INSERT INTO `bookshop`.`Order_items`
(`order_id`,
`book_id`,
`amount`)
VALUES
(1,1,2),
(1,2,1),
(2,1,1),
(3,5,1);


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




