show databases;

create database cloud3;

use cloud3;

create table member(
	email varchar(50) primary key,
    pw varchar(20) not null,
    tel varchar(20) not null,
    address varchar(100) not null
);

commit;

select * from member;

delete from member where email='jay@gmail.com';