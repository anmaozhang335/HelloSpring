use o2o;
CREATE TABLE `tb_shop`(
  `shop_id`int(10) not null AUTO_INCREMENT, 
  `owner_id` int(10) not null COMMENT'店铺创建人',
  `area_id`int(5) DEFAULT NULL,
  `shop_category_id` int(11) DEFAULT NULL,
  `shop_name` varchar(256) not null,
  `shop_desc` varchar(1024) DEFAULT NULL,
  `shop_addr` varchar(200) DEFAULT NULL,
  `phone` varchar(128) DEFAULT null,
  `shop_img` varchar(1024) DEFAULT NULL,
  `priority` int(3) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` int(2) DEFAULT '0',
  `advice` varchar(255)DEFAULT NULL,
  PRIMARY KEY(`shop_id`),
  CONSTRAINT `fk_shop_area` FOREIGN KEY(`area_id`) REFERENCES `tb_area`(`area_id`),
  CONSTRAINT `fk_shop_profile` FOREIGN KEY(`owner_id`) REFERENCES `tb_person_info`(`user_id`),
  CONSTRAINT `fk_shop_shopcate` FOREIGN KEY(`shop_category_id`) REFERENCES `tb_shop_category`(`shop_category_id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;