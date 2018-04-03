use o2o;
create table `tb_local_auth`(
`local_auth_id`int(10) not null auto_increment,
`user_id` int(10) not null,
`username` varchar(128) not null,
`password` varchar(128) not null,
`create_time` datetime default null,
`last_edit_time`datetime default null,
PRIMARY KEY(`local_auth_id`),
UNIQUE KEY`uk_local_profile`(`username`),
CONSTRAINT `fk_localauth_profile` FOREIGN KEY(`user_id`) REFERENCES `tb_person_info`(`user_id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;