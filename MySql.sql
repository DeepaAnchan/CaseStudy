show databases;
create database mydb;

use mydb;
show tables;


select * from product;
drop table product ;

create table product(
id int auto_increment PRIMARY KEY,
product_name varchar(20),
product_details varchar(100),
price int 
);

INSERT INTO product (product_name, product_details , price) VALUES ('Pen','A blue ink pen.',10);
