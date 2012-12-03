--This is script was developed for MySQL 5 or above.

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
);

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

insert into study_main_subject (study_area_id,description) values (1, 'Sociologia'),(1,'Administração'),(1,'Filosofia');
insert into study_main_subject (study_area_id,description) values (2, 'Medicina'),(2,'Biologia'),(2,'Farmácia');
insert into study_main_subject (study_area_id,description) values (3, 'Engenharia'),(3,'Matemática'),(3,'Física'),(3,'Informática');


drop table if exists study_subject;
create table study_subject (
study_subject_id int(10) unsigned not null auto_increment,
study_main_subject_id int(10) unsigned not null,
description varchar(100) not null,
unique key uk_study_main_subject(description),
constraint fk_subject_main_subject foreign key fk_subject_main_subject (study_main_subject_id) references study_main_subject (study_main_subject_id),
primary key(study_subject_id)
);

set @study_main_subject_id=(select study_main_subject_id from study_main_subject where description='Informática');
select @study_main_subject_id;


insert into study_subject (study_main_subject_id,description) values (@study_main_subject_id, 'Intel Hackathon');

drop table if exists user (
user_id
);

drop table if exists study_group;
create group study_group;

(
);
