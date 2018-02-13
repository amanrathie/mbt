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

-- UF
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('AC', 'Acre');
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('AL',	'Alagoas');
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('AM',	'Amazonas');
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('AP',	'Amapá');
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('BA',	'Bahia');
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('CE',	'Ceará');
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('DF',	'Distrito Federal');
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('ES',	'Espírito Santo');
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('GO',	'Goiás');
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('MA',	'Maranhão');
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('MG',	'Minas Gerais');
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('MS',	'Mato Grosso do Sul');
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('MT',	'Mato Grosso');
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('PA',	'Pará');
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('PB',	'Paraíba');
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('PE',	'Pernambuco');
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('PI',	'Piauí');
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('PR',	'Paraná');
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('RJ',	'Rio de Janeiro');
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('RN',	'Rio Grande do Norte');
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('RO',	'Rondônia');
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('RR',	'Roraima');
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('RS',	'Rio Grande do Sul');
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('SC',	'Santa Catarina');
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('SE',	'Sergipe');
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('SP',	'São Paulo');
INSERT INTO dbo.Vw_UF (SigUF, DescUF) values('TO',	'Tocantins');
	