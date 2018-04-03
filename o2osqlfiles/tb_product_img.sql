use o2o;
create table `tb_product_img`(
`product_img_id` int(20) not null AUTO_INCREMENT,
`img_addr` varchar(2000) not null,
`img_desc` varchar(2000) DEFAULT NULL,
`priority` int(2) DEFAULT'0',
`create_time` datetime DEFAULT NULL,
`product_id` int(20) DEFAULT NULL,
PRIMARY KEY(`product_img_id`),
CONSTRAINT `fk_proimg_product` FOREIGN KEY(`product_id`) REFERENCES `tb_product`(`product_id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;