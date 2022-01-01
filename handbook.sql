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

-- 删除bill表
-- drop table if exists bill;
-- 创建bill表
create table if not exists bill(
`id` INT(11) auto_increment primary key,
`money` double not null,
`label` varchar(40) not null,
`comment` varchar(400) not null,
`calendar` varchar(100) not null,
`ctgr` enum("1","0") not null,
`user_id` int not null,
constraint bill_user_id foreign key(`user_id`) references user(`id`)
)ENGINE=InnoDB DEFAULT charset=utf8;

-- select * from bill;

-- insert into bill(`money`,`label`,`comment`,`calendar`,`ctgr`,`user_id`) values(5600,"工资薪水", "salary for 12", "2021.12.31","1",1);
-- delete from bill where user_id `user_id` = 
-- select `money`,`label`,`comment`,`calendar`,`ctgr` from bill where user_id = 1;



