use o2o;
create table `tb_wechat_auth`(
`wechat_auth_id`int(10)not null AUTO_INCREMENT,
`user_id` int(10)not null,
`open_id` varchar(1024) not null,
`create_time`datetime DEFAULT NULL,
PRIMARY KEY(`wechat_auth_id`),
CONSTRAINT `fk_wechatauth_profile` FOREIGN KEY(`user_id`) REFERENCES `tb_person_info`(`user_id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;