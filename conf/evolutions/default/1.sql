# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table activity_to_do (
  id                        bigint not null,
  name                      varchar(255),
  description               varchar(255),
  num_people                integer,
  constraint pk_activity_to_do primary key (id))
;

create table user (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (email))
;


create table activity_to_do_user (
  activity_to_do_id              bigint not null,
  user_email                     varchar(255) not null,
  constraint pk_activity_to_do_user primary key (activity_to_do_id, user_email))
;
create sequence activity_to_do_seq;

create sequence user_seq;




alter table activity_to_do_user add constraint fk_activity_to_do_user_activi_01 foreign key (activity_to_do_id) references activity_to_do (id) on delete restrict on update restrict;

alter table activity_to_do_user add constraint fk_activity_to_do_user_user_02 foreign key (user_email) references user (email) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists activity_to_do;

drop table if exists activity_to_do_user;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists activity_to_do_seq;

drop sequence if exists user_seq;

