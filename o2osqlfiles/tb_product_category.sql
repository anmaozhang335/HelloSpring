use o2o;
CREATE TABLE `tb_product_category`(
`product_category_id` int(11) not null AUTO_INCREMENT,
`product_category_name` varchar(100) not null,
`priority`int(2) DEFAULT'0',
`create_time` datetime DEFAULT NULL,
`shop_id` int(20) not null DEFAULT'0',
PRIMARY KEY(`product_category_id`),
CONSTRAINT `fk_procate_shop` FOREIGN KEY(`shop_id`) REFERENCES `tb_shop`(`shop_id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;