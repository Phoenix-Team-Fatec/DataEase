use dataease;

create table usuarios (
	idusuarios int not null primary key auto_increment,
    nome varchar(50),
    senha varchar(50)
);

create table banco_de_dados (
	id_banco_dados int not null primary key auto_increment,
    nome_db varchar(50),
    id_usuario int
);

select * from usuarios;

select * from banco_de_dados;