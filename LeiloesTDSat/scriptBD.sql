create database UC11Atividade02;
use UC11Atividade02;

create table PRODUTOS(
	id int primary key auto_increment,
    nome varchar(30),
    valor float(10,2),
    status varchar(20)
);