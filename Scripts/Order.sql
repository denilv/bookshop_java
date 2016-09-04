USE bookshop;
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

select * from `bookshop`.`Orders`;