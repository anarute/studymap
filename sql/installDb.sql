#--This is script was developed for MySQL 5 or above.

create database study_map;
use study_map;

drop table if exists study_subject;
drop table if exists study_main_subject;
drop table if exists study_area;
create table study_area (
study_area_id int(10) unsigned not null auto_increment,
description varchar(100) not null,
unique key uk_study_area_description(description),
primary key(study_area_id)
)charset=utf8;

insert into study_area (description) values ('Humanas'),('Biologicas'),('Exatas');

drop table if exists study_subject;
drop table if exists study_main_subject;
create table study_main_subject (
study_main_subject_id int(10) unsigned not null auto_increment,
study_area_id int(10) unsigned not null,
description varchar(100) not null,
unique key uk_study_main_subject(description),
constraint fk_main_subjetc_area foreign key fk_main_subjetc_area (study_area_id) references study_area (study_area_id),
primary key(study_main_subject_id));

insert into study_main_subject (study_area_id,description) values (1, 'Sociologia'),(1,'Administrao'),(1,'Filosofia');
insert into study_main_subject (study_area_id,description) values (2, 'Medicina'),(2,'Biologia'),(2,'Farmcia');
insert into study_main_subject (study_area_id,description) values (3, 'Engenharia'),(3,'Matematica'),(3,'Fisica'),(3,'Informatica');


drop table if exists study_subject;
create table study_subject (
study_subject_id int(10) unsigned not null auto_increment,
study_main_subject_id int(10) unsigned not null,
description varchar(100) not null,
unique key uk_study_main_subject(description),
constraint fk_subject_main_subject foreign key fk_subject_main_subject (study_main_subject_id) references study_main_subject (study_main_subject_id),
primary key(study_subject_id)
);

set @study_main_subject_id=(select study_main_subject_id from study_main_subject where description='Informtica');
select @study_main_subject_id;


insert into study_subject (study_main_subject_id,description) values (@study_main_subject_id, 'Intel Hackathon');

drop table if exists users;
create table users (
user_id  int(10) unsigned not null auto_increment,
login varchar(100) not null,
email varchar(255) not null,
unique key uk_user_login (login),
unique key uk_user_email (email),
primary key (user_id)
);

insert into users (login, email) values ('ana', 'anaruth@hack.com'), ('murilo', 'murilo@hach.com'), ('david', 'david@hack.com'), ('joao', 'joao@hack.com');

drop table if exists study_group;
create table study_group (
study_group_id  int(10) unsigned not null auto_increment,
owner_id int(10) unsigned not null,
study_subject_id int(10) unsigned not null,
description varchar(255) not null,
longitude float(9,6) not null,
latitude float(9,6) not null,
constraint fk_study_group_owner foreign key fk_study_group_owner (owner_id) references users (user_id),
constraint fk_study_group_subject foreign key fk_study_group_subject (study_subject_id) references study_subject (study_subject_id),
primary key(study_group_id)
);

insert into study_group (owner_id, study_subject_id, description, longitude, latitude) values (1, 1, 'Intel Hackton Unicamp 2012', -22.813707, -47.063591);

drop table if exists study_group_member;
create table study_group_member (
study_group_memeber_id  int(10) unsigned not null auto_increment,
study_group_id  int(10) unsigned not null,
user_id  int(10) unsigned not null,
constraint fk_group_member_group foreign key fk_group_member_group (study_group_id) references study_group (study_group_id),
constraint fk_group_member_user foreign key fk_group_member_user (user_id) references users (user_id),
unique key uk_study_group_member (study_group_id, user_id),
primary key(study_group_memeber_id)
);

insert into study_group_member (study_group_id, user_id) values (1,2), (1,3), (1,4);

create table study_group_post (
study_group_post_id int(10) unsigned not null auto_increment,
study_group_id int(10) unsigned not null,
user_id int(10) unsigned not null,
title varchar(144) not null,
content mediumtext not null,
posted timestamp not null,
constraint fk_study_group_post_group foreign key fk_study_group_post_group (study_group_id) references study_group (study_group_id),
constraint fk_study_group_post_user foreign key fk_study_group_post_user (user_id) references users (user_id),
primary key (study_group_post_id)
);

insert into study_group_post (study_group_id, user_id, title, content) values (1,2,"What's up test!", "Hi, this is a test, it's alive!!!");

drop table if exists study_group_schedule;
create table study_group_schedule (
study_group_schedule_id int(10) unsigned not null auto_increment,
study_group_id int(10) unsigned not null,
day_of_week tinyint(1) comment 'Migth be changed to enum',
year int(4),
month tinyint(2),
day_of_month tinyint(2),
hour tinyint(2) not null,
minute tinyint(2) not null,
constraint fk_study_group_schedule_group foreign key fk_study_group_schedule_group (study_group_id) references study_group (study_group_id),
primary key(study_group_schedule_id)) comment 'based on crontab';


insert into study_group_schedule (study_group_id, year, month, day_of_month, hour, minute) values (1,2012,12,3,9,0);
insert into study_group_schedule (study_group_id, year, month, day_of_month, hour, minute) values (1,2012,12,4,9,0);

