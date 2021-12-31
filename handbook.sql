create database if not exists handbook;

use handbook;
-- 删除user表
-- drop table if exists user;
-- 创建user表
create table if not exists user(
`id` INT(11) auto_increment primary key,
`username` varchar(40) not null unique,
`password` varchar(40) not null
)ENGINE=InnoDB DEFAULT charset=utf8;

-- insert user(`username`,`password`) values("admin","123456");

-- select * from user;