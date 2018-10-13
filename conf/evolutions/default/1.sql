# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tbBill (
  id                        varchar(255) not null,
  date                      datetime,
  user_id                   varchar(255),
  status                    varchar(255),
  constraint pk_tbBill primary key (id))
;

create table tbBillDetail (
  bill_id                   varchar(255),
  book_id                   varchar(255),
  amount                    integer)
;

create table tbBook (
  id                        varchar(255) not null,
  title                     varchar(255),
  author                    varchar(255),
  pages                     integer,
  price                     double,
  constraint pk_tbBook primary key (id))
;

create table tbHuman (
  id                        varchar(255) not null,
  name                      varchar(255),
  surname                   varchar(255),
  gender                    tinyint(1) default 0,
  constraint pk_tbHuman primary key (id))
;

create table tbNoteBook (
  id                        varchar(255) not null,
  title                     varchar(255),
  author                    varchar(255),
  pages                     integer,
  price                     double,
  publisher_id              varchar(255),
  constraint pk_tbNoteBook primary key (id))
;

create table tbOrders (
  id                        varchar(255) not null,
  date                      datetime,
  status                    varchar(255),
  constraint pk_tbOrders primary key (id))
;

create table tbOrdersDetail (
  id                        varchar(255) not null,
  orders_id                 varchar(255),
  book_id                   varchar(255),
  amount                    integer,
  constraint pk_tbOrdersDetail primary key (id))
;

create table tbPublisher (
  id                        varchar(255) not null,
  name                      varchar(255),
  address                   varchar(255),
  constraint pk_tbPublisher primary key (id))
;

create table tbUser (
  id                        varchar(255) not null,
  name                      varchar(255),
  status                    varchar(255),
  password                  varchar(255),
  constraint pk_tbUser primary key (id))
;

alter table tbBill add constraint fk_tbBill_user_1 foreign key (user_id) references tbUser (id) on delete restrict on update restrict;
create index ix_tbBill_user_1 on tbBill (user_id);
alter table tbBillDetail add constraint fk_tbBillDetail_bill_2 foreign key (bill_id) references tbBill (id) on delete restrict on update restrict;
create index ix_tbBillDetail_bill_2 on tbBillDetail (bill_id);
alter table tbBillDetail add constraint fk_tbBillDetail_book_3 foreign key (book_id) references tbBook (id) on delete restrict on update restrict;
create index ix_tbBillDetail_book_3 on tbBillDetail (book_id);
alter table tbNoteBook add constraint fk_tbNoteBook_publisher_4 foreign key (publisher_id) references tbPublisher (id) on delete restrict on update restrict;
create index ix_tbNoteBook_publisher_4 on tbNoteBook (publisher_id);
alter table tbOrdersDetail add constraint fk_tbOrdersDetail_orders_5 foreign key (orders_id) references tbOrders (id) on delete restrict on update restrict;
create index ix_tbOrdersDetail_orders_5 on tbOrdersDetail (orders_id);
alter table tbOrdersDetail add constraint fk_tbOrdersDetail_book_6 foreign key (book_id) references tbBook (id) on delete restrict on update restrict;
create index ix_tbOrdersDetail_book_6 on tbOrdersDetail (book_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table tbBill;

drop table tbBillDetail;

drop table tbBook;

drop table tbHuman;

drop table tbNoteBook;

drop table tbOrders;

drop table tbOrdersDetail;

drop table tbPublisher;

drop table tbUser;

SET FOREIGN_KEY_CHECKS=1;

