----创建用户表----
DROP TABLE pub_users;
CREATE TABLE `pub_users` (
  `user_id` varchar(32) NOT NULL,
  `user_name` varchar(32) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `login_time` datetime DEFAULT NULL,
  `login_ip` varchar(32) DEFAULT NULL,
  `login_out_time` datetime DEFAULT NULL,
  `user_email` varchar(255) NOT NULL,
	`isLogin` char(1) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
--修改表结构类型--
alter table pub_users modify column login_time varchar(19)