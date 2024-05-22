create database DataEase;
use DataEase;

select * from usuarios;
select * from banco_de_dados;

insert into usuarios(id_usuario, nome, senha, id_banco_dados) VALUES
(001 , 'Pedro' , '12345' , 001);

insert into banco_de_dados(id_banco_dados, nome_db, id_usuario) VALUES
(001 , 'primeiro_db', 001);
insert into banco_de_dados(id_banco_dados, nome_db, id_usuario) VALUES
(002 , 'segundo_db', 001);

-- Java SELECT	
SELECT u.nome AS nome_usuario, bd.nome_db AS nome_banco_de_dados
FROM usuarios u
JOIN banco_de_dados bd ON u.id_usuario = bd.id_usuario;