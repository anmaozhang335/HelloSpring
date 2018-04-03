use o2o;
CREATE TABLE `tb_shop_category`(
`shop_category_id` int(11) not null AUTO_INCREMENT,
`shop_category_name`varchar(100) not null DEFAULT '',
`shop_category_desc`varchar(1000) DEFAULT '',
`shop_category_img`varchar(2000)DEFAULT null,
`priority` int(2) not null DEFAULT'0',
`create_time` datetime DEFAULT null,
`last_edit_time` datetime DEFAULT null,
`parent_id` int(11) DEFAULT null,
PRIMARY KEY(`shop_category_id`),
CONSTRAINT `fk_shop_category_self` FOREIGN KEY(`parent_id`) REFERENCES `tb_shop_category`(`shop_category_id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT charset=utf8;