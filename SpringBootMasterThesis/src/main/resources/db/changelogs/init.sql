drop table if exists author cascade;
drop table if exists author_book cascade;
drop table if exists book cascade;
drop table if exists category cascade;
drop table if exists category_book cascade;
drop table if exists order_item cascade;
drop table if exists orders cascade;
drop table if exists roles cascade;
drop table if exists users cascade;
create table author (author_id  bigserial not null, first_name varchar(255), last_name varchar(255), primary key (author_id));
create table author_book (book_id int8 not null, author_id int8 not null, primary key (book_id, author_id));
create table book (book_id  bigserial not null, amount int4 not null, is_deleted boolean not null, price float8 not null, title varchar(255), primary key (book_id));
create table category (category_id  bigserial not null, is_deleted boolean not null, name varchar(255), primary key (category_id));
create table category_book (book_id int8 not null, category_id int8 not null, primary key (book_id, category_id));
create table order_item (order_item_id  bigserial not null, amount int4 not null, book_id int8, order_id int8, primary key (order_item_id));
create table orders (order_id  bigserial not null, order_date timestamp, total float8 not null, user_id int8, primary key (order_id));
create table roles (role_id  bigserial not null, name varchar(255), primary key (role_id));
create table users (user_id  bigserial not null, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), username varchar(255), role_id int8, primary key (user_id));
alter table author_book add constraint FKg7j6ud9d32ll232o9mgo90s57 foreign key (author_id) references author;
alter table author_book add constraint FKn8665s8lv781v4eojs8jo3jao foreign key (book_id) references book;
alter table category_book add constraint FKopci7vhx9bwg4bjuunnr58pax foreign key (category_id) references category;
alter table category_book add constraint FKi54mcir5dsoaqyydt28lht3w4 foreign key (book_id) references book;
alter table order_item add constraint FKb033an1f8qmpbnfl0a6jb5njs foreign key (book_id) references book;
alter table order_item add constraint FKt4dc2r9nbvbujrljv3e23iibt foreign key (order_id) references orders;
alter table orders add constraint FK32ql8ubntj5uh44ph9659tiih foreign key (user_id) references users;
alter table users add constraint FKp56c1712k691lhsyewcssf40f foreign key (role_id) references roles;