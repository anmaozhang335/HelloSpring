use o2o;
create table `tb_product`(
`product_id` int(100) not null AUTO_INCREMENT,
`product_name` varchar(100) not null,
`product_desc` varchar(2000) DEFAULT NULL,
`img_addr` varchar(2000) DEFAULT'',
`normal_price`varchar(100) DEFAULT null,
`promotion_price` varchar(100) DEFAULT NULL,
`priority` int(2) not null DEFAULT '0',
`create_time`datetime DEFAULT NULL,
`last_edit_time` datetime DEFAULT NULL,
`enable_status`int(2) NOT NULL DEFAULT'0',
`product_category_id` int(11) DEFAULT NULL,
`shop_id` int(10) NOT NULL DEFAULT'0',
PRIMARY KEY(`product_id`),
CONSTRAINT `fk_product_procate` FOREIGN KEY(`product_category_id`) REFERENCES `tb_product_category`(`product_category_id`),
CONSTRAINT `fk_product_shop` FOREIGN KEY(`shop_id`) REFERENCES `tb_shop`(`shop_id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;