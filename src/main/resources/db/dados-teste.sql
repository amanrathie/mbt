-- Utilizado para popular base de testes que será usada para as queries builders, repositories etc


-- Avaliações
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase) 
	VALUES (0, 'Escala Brasil Transparente', 1, 0, 5);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase) 
	VALUES (1, 'Escala Brasil Transparente', 2, 0, 5);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase) 
	VALUES (2, 'Escala Brasil Transparente', 3, 0, 5);
	
-- Resultados de avaliações
INSERT INTO dbo.ResultadoAvaliacao(IdResultadoAvaliacao, IdAvaliacao, NomMunicipio, ValNota)
	VALUES (0, 0, 'Brasília', 10);

INSERT INTO dbo.ResultadoAvaliacao(IdResultadoAvaliacao, IdAvaliacao, NomMunicipio, ValNota)
	VALUES (1, 0, 'São Paulo', 9);	
	
	