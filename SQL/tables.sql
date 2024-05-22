create database DataEase;
use DataEase;


CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE banco_de_dados (
    id_banco_dados INT AUTO_INCREMENT PRIMARY KEY not null,
    nome_db VARCHAR(255) NOT NULL,
    id_usuario INT NOT NULL

);


create table instances(
  id_instances int auto_increment primary key not null,
  nome_instances varchar(30) not null,
  id_usuario INT NOT NULL

);


create table users(
    id_users int auto_increment primary key not null,
    name_users varchar(30) not null,
    id_usuario INT NOT NULL

);

create table passwords(
   id_passwords int auto_increment primary key not null,
   users_passwords varchar(30) not null,
   id_usuario INT NOT NULL
 

);


select * from banco_de_dados;

select * from passwords;

select * from users;

select * from instances;

select * from usuarios
