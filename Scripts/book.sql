use bookshop;
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

select * from Book;
