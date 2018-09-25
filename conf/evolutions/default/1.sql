# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

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

create table tbPublisher (
  id                        varchar(255) not null,
  name                      varchar(255),
  address                   varchar(255),
  constraint pk_tbPublisher primary key (id))
;

alter table tbNoteBook add constraint fk_tbNoteBook_publisher_1 foreign key (publisher_id) references tbPublisher (id) on delete restrict on update restrict;
create index ix_tbNoteBook_publisher_1 on tbNoteBook (publisher_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table tbBook;

drop table tbHuman;

drop table tbNoteBook;

drop table tbPublisher;

SET FOREIGN_KEY_CHECKS=1;

