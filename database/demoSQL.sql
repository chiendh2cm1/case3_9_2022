drop database if exists chiendemo;
create database chiendemo;
use chiendemo;
create table product
(
    ProductID       varchar(20)  not null primary key unique,
    CategoryID      varchar(20)   null,
    ProductName     varchar(255) not null,
    ProductPrice    float        not null,
    QuantityInStock int          not null,
    Image           varchar(255) not null,
    Status          bit          not null,
    Description     text         not null
);
use chiendemo;

create table category
(
    CategoryID   varchar(20)  not null primary key unique,
    CategoryName varchar(255) not null
);

alter table product
    add foreign key (CategoryID) references category (CategoryID)
        on update cascade;

create table roll
(
    rollId        varchar(20) not null,
    AccountAccess bit         not null  unique
);

create table account
(
    AccountID     varchar(20)   not null primary key unique,
    AccountName   varchar(255) not null,
    LoginName     varchar(50)   not null,
    Password      varchar(50)   not null,
    AccountAccess bit           null,
    Address       varchar(255)  not null,
    PhoneNumber   varchar(255)  not null,
    Gender        bit           null,
    Status        bit           null
);

alter table account
    add foreign key (AccountAccess) references roll (AccountAccess);

create table orders
(
    OrderID     varchar(20) not null primary key unique,
    AccountID   varchar(20)   null,
    OrderDate   datetime    not null default now(),
    Receiver    varchar(255),
    Address     varchar(255),
    Email       varchar(50),
    PhoneNumber varchar(20),
    Status      bit                  default 1
);

alter table orders
    add foreign key (AccountID) references account (AccountID)
        on update cascade;

create table order_product
(
    OrderID   varchar(20) not null,
    ProductID varchar(20) not null,
    Quantity  int         not null,
    PriceEach float,
    AccountID varchar(20) not null,
    primary key (OrderID, ProductID)
);

alter table order_product
    add foreign key (OrderID) references orders (OrderID) on update cascade,
    add foreign key (ProductID) references product (ProductID) on update cascade;

insert into roll (rollId, AccountAccess)
values (1, 1),
       (2, 0);

insert into category
values ('1', 'Cây Xanh Trang Trí'),
       ('2', 'Hoa Tân Gia'),
       ('3', 'Hoa Sinh Nhật');

insert into product (ProductID, CategoryID, ProductName, ProductPrice, QuantityInStock, Image, Status, Description)
values  ('9', '2', 'Bình hoa đào đông', 300000, 100,'img/product/tangia/891-6158.webp', 1,
         'Gồm cả bình hoa từ gốm bát tràng, hoa mang vẻ đẹp bình dị nhẹ nhàng'),
        ('10', '2', 'Bình hoa trang tri 1', 200000, 100,'img/product/tangia/891-6213.webp', 1,
         'Gồm cả bình hoa từ gốm bát tràng, hoa mang vẻ đẹp bình dị nhẹ nhàng'),
        ('11', '2', 'Bình hoa trang tri 2', 300000, 100,'img/product/tangia/nh281.webp', 1,
         'Gồm cả bình hoa từ gốm bát tràng, hoa mang vẻ đẹp bình dị nhẹ nhàng'),
        ('12', '2', 'Bình hoa lan nhỏ', 300000, 100,'img/product/tangia/nh284-1.webp', 1,
         'Gồm cả bình hoa từ gốm bát tràng, hoa mang vẻ đẹp bình dị nhẹ nhàng'),
        ('13', '2', 'Bình hoa lan lớn', 300000, 100,'img/product/tangia/sau894.webp', 1,
         'Gồm cả bình hoa từ gốm bát tràng, hoa mang vẻ đẹp bình dị nhẹ nhàng'),
        ('14', '2', 'Bình hoa đào đông lớn', 300000, 100,'img/product/tangia/thuy250.webp', 1,
         'Gồm cả bình hoa từ gốm bát tràng, hoa mang vẻ đẹp bình dị nhẹ nhàng'),
        ('15', '2', 'Bình hoa ly', 100000, 100,'img/product/tangia/thuy252.webp', 1,
         'Gồm cả bình hoa từ gốm bát tràng, hoa mang vẻ đẹp bình dị nhẹ nhàng'),
        ('16', '2', 'Bình hoa lê hồng', 100000, 100,'img/product/tangia/z3122054453975-ed54217fc782527d1801643d7a4fff2c.webp', 1,
         'Gồm cả bình hoa từ gốm bát tràng, hoa mang vẻ đẹp bình dị nhẹ nhàng'),
        ('17', '3', 'Bình hoa tặng 1', 100000, 100,'img/product/sinhnhat/891-6334.webp', 1,
         'Gồm cả bình hoa từ gốm bát tràng, hoa mang vẻ đẹp bình dị nhẹ nhàng'),
        ('18', '3', 'Giỏ hoa tặng 2', 100000, 100,'img/product/sinhnhat/891-6366.webp', 1,
         'Gồm giỏ cói bền lâu hỏng, hoa mang vẻ đẹp bình dị nhẹ nhàng'),
        ('19', '3', 'Giỏ hoa tặng 3', 100000, 100,'img/product/sinhnhat/891-6369.webp', 1,
         'Gồm giỏ cói bền lâu hỏng, hoa mang vẻ đẹp bình dị nhẹ nhàng'),
        ('20', '3', 'Giỏ hoa tặng 2', 100000, 100,'img/product/sinhnhat/nh285-2.webp', 1,
         'Gồm giỏ cói bền lâu hỏng, hoa mang vẻ đẹp bình dị nhẹ nhàng'),
        ('21', '3', 'Giỏ hoa tặng 2', 100000, 100,'img/product/sinhnhat/thuy238.webp', 1,
         'Gồm giỏ cói bền lâu hỏng, hoa mang vẻ đẹp bình dị nhẹ nhàng'),
        ('22', '3', 'Giỏ hoa tặng 2', 100000, 100,'img/product/sinhnhat/thuy251.webp', 1,
         'Gồm giỏ cói bền lâu hỏng, hoa mang vẻ đẹp bình dị nhẹ nhàng'),
        ('23', '3', 'Giỏ hoa tặng 2', 100000, 100,'img/product/sinhnhat/thuy251.webp', 1,
         'Gồm giỏ cói bền lâu hỏng, hoa mang vẻ đẹp bình dị nhẹ nhàng'),
        ('24', '3', 'Bình hoa tặng 2', 100000, 100,'img/product/sinhnhat/thuy257.webp', 1,
         'Gồm cả bình hoa làm từ gốm bát tràng, hoa mang vẻ đẹp bình dị nhẹ nhàng'),
        ('1', '1', 'Cây lan thủy mặc trắng', 500000, 100,'img/product/cay/cay-co-gia-1.webp', 1,
         '<p>Gồm giá cây không chậu, cây mang vẻ đẹp bình dị nhẹ nhàng<br>'),
        ('2', '1', 'Cây lan thủy mặc hồng', 500000, 50, 'img/product/cay/cay-co-gia-2.webp', 1,
         '<p>Gồm giá cây không chậu, cây mang vẻ đẹp bình dị nhẹ nhàng<br>'),
        ('3', '1', 'Cây cỏ thác đá xanh', 475000, 100, 'img/product/cay/cay-gia-in-bloom-4.webp', 1,
         '<p>Gồm giá cây không chậu, cây mang vẻ đẹp bình dị nhẹ nhàng<br>'),
        ('4', '1', 'Cây chuối cảnh', 300000, 50, 'img/product/cay/cay-gia-in-bloom-6.webp', 1,
         '<p>Gồm giá cây không chậu, cây mang vẻ đẹp bình dị nhẹ nhàng<br>'),
        ('5', '1', 'Cây lan hoàng quân', 500000, 50, 'img/product/cay/cay-gia-in-bloom-7.webp', 1,
         '<p>Gồm giá cây không chậu, cây mang vẻ đẹp bình dị nhẹ nhàng<br>'),
        ('6', '1', 'Cây cỏ lá rộng', 200000, 50, 'img/product/cay/cay-gia-in-bloom-8.webp', 1,
         '<p>Gồm giá cây không chậu, cây mang vẻ đẹp bình dị nhẹ nhàng<br>'),
        ('7', '1', 'Cây cau', 500000, 50, 'img/product/cay/cay-gia-in-bloom-9.webp', 1,
         '<p>Gồm giá cây không chậu, cây mang vẻ đẹp bình dị nhẹ nhàng<br>'),
        ('8', '1', 'Cây oliu', 500000, 50, 'img/product/cay/jan-2020-2.webp', 1,
         '<p>Gồm giá cây không chậu, cây mang vẻ đẹp bình dị nhẹ nhàng<br>');


insert into account (AccountID, AccountName, LoginName, Password, AccountAccess, Address, PhoneNumber, Gender, Status)
values ('AD1', 'admin', 'admin', 'admin', 1, '222 doi can', '0913026630', 0, 1),
       ('KH1', 'chien', 'chien', 'chien', 0, 'DA', '0966351299', 0, 1);


delimiter //
create procedure  delete_category (
    in inputId varchar(20)
)
begin
update product set CategoryID = null where CategoryID = inputId;
delete  from  category where CategoryID = inputId;
end //

delimiter ;

DELIMITER //
create procedure delete_account(in accountId1 varchar(20))
BEGIN
update orders set AccountID = null where AccountID = accountId1;
delete from account where AccountID = accountId1;
end //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE update_password_by_id(in account_id varchar(20),in password_in varchar(20))
begin
alter table account modify Password varchar(20) null;
update account set Password = password_in where AccountID = account_id;
alter table account modify Password varchar(20) not null;
end //

DELIMITER ;

DELIMITER //
CREATE PROCEDURE delete_order_by_id(in order_id varchar(20))
BEGIN
delete from order_product where OrderID = order_id;
delete from orders where  OrderID = order_id;
end //

DELIMITER ;


