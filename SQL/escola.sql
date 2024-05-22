create database escola;
use escola;

CREATE TABLE Sala (
ID_Sala INT PRIMARY KEY AUTO_INCREMENT,
Nome_Sala VARCHAR(100),
Localizacao VARCHAR(100)
);

CREATE TABLE Equipamento (
ID_Equipamento INT PRIMARY KEY AUTO_INCREMENT,
Nome_Equipamento VARCHAR(100),
Descricao VARCHAR(255)
);

CREATE TABLE Sala_Equipamento (
ID_Sala INT,
ID_Equipamento INT,
Quantidade INT,
PRIMARY KEY (ID_Sala, ID_Equipamento),
FOREIGN KEY (ID_Sala) REFERENCES Sala(ID_Sala),
FOREIGN KEY (ID_Equipamento) REFERENCES Equipamento(ID_Equipamento)
);

INSERT INTO Sala (Nome_Sala, Localizacao) VALUES
('Sala de Reunião', 'Andar 1, Bloco A'),
('Sala de Treinamento', 'Andar 2, Bloco B'),
('Sala de Conferências', 'Andar 3, Bloco C');

INSERT INTO Equipamento (Nome_Equipamento, Descricao) VALUES
('Projetor', 'Projetor de alta definição'), ('Computador', 'Desktop com processador i7 e 16GB de RAM'), ('Mesa', 'Mesa de reunião para até 10 pessoas');

INSERT INTO Sala_Equipamento (ID_Sala, ID_Equipamento, Quantidade) VALUES
(1, 1, 1), -- Um projetor na Sala de Reunião
(2, 2, 10), -- Dez computadores na Sala de Treinamento
(3, 3, 1), -- Uma mesa na Sala de Conferências
(1, 2, 2), -- Dois computadores na Sala de Reunião
(3, 1, 1); -- Um projetor na Sala de Conferências

SELECT e.Nome_Equipamento, se.Quantidade 
FROM Equipamento e, Sala_Equipamento se 
where e.ID_Equipamento = se.ID_Equipamento and se.ID_Sala = 1;


SELECT s.Nome_Sala, s.Localizacao FROM Sala s INNER JOIN Sala_Equipamento se ON s.ID_Sala = se.ID_Sala INNER JOIN Equipamento e ON se.ID_Equipamento = e.ID_Equipamento WHERE e.Nome_Equipamento = 'Projetor';

SELECT s.Nome_Sala, e.Nome_Equipamento, e.Descricao, se.Quantidade
FROM Sala s
LEFT JOIN Sala_Equipamento se ON s.ID_Sala = se.ID_Sala
LEFT JOIN Equipamento e ON se.ID_Equipamento = e.ID_Equipamento
ORDER BY s.Nome_Sala;

SELECT COUNT(*) AS Total_Equipamentos

FROM Sala_Equipamento

WHERE ID_Sala = 1;
