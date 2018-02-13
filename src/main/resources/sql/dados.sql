-- Utilizado para popular base de testes que será usada para as queries builders, repositories etc


-- Avaliações
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, IdEntidadeAvaliadora) 
	VALUES (0, 'Escala Brasil Transparente', 1, 0, 5, 0);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, IdEntidadeAvaliadora) 
	VALUES (1, 'Escala Brasil Transparente', 2, 0, 5, 0);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, IdEntidadeAvaliadora) 
	VALUES (2, 'Escala Brasil Transparente', 3, 0, 5, 0);
	
-- Resultados de avaliações
INSERT INTO dbo.ResultadoAvaliacao(IdResultadoAvaliacao, IdAvaliacao, NomMunicipio, ValNota)
	VALUES (0, 0, 'Brasília', 10);

INSERT INTO dbo.ResultadoAvaliacao(IdResultadoAvaliacao, IdAvaliacao, NomMunicipio, ValNota)
	VALUES (1, 0, 'São Paulo', 9);	
	
-- Entidades Avaliadoras
INSERT INTO dbo.EntidadeAvaliadora (IdEntidadeAvaliadora, IdTipoEntidade, NomEntidade, FlgCGU) values (1, 1, 'Entidade Avaliadora 1', 0);
INSERT INTO dbo.EntidadeAvaliadora (IdEntidadeAvaliadora, IdTipoEntidade, NomEntidade, FlgCGU) values (2, 1, 'Entidade Avaliadora 2', 0);
INSERT INTO dbo.EntidadeAvaliadora (IdEntidadeAvaliadora, IdTipoEntidade, NomEntidade, FlgCGU) values (3, 1, 'Entidade Avaliadora 3', 0);
INSERT INTO dbo.EntidadeAvaliadora (IdEntidadeAvaliadora, IdTipoEntidade, NomEntidade, FlgCGU) values (4, 1, 'Entidade Avaliadora 4', 0);
INSERT INTO dbo.EntidadeAvaliadora (IdEntidadeAvaliadora, IdTipoEntidade, NomEntidade, FlgCGU) values (5, 1, 'Entidade Avaliadora 5', 0);
	