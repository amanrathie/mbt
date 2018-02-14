-- Utilizado para popular base de testes que será usada apenas para os testes
	
-- Avaliações
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, IdPoder, IdEntidadeAvaliadora, 
	IdUsuarioAdministrador, IdUsuarioCGU, FlgAtiva) 
	VALUES (0, 'Escala Brasil Transparente', 1, 0, 5, 0, 0, 
	0, 1, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, IdPoder, IdEntidadeAvaliadora, 
	IdUsuarioAdministrador, IdUsuarioCGU, FlgAtiva) 
	VALUES (1, 'Escala Brasil Transparente', 2, 0, 5, 0, 0, 
	0, 1, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, IdPoder, IdEntidadeAvaliadora, 
	IdUsuarioAdministrador, IdUsuarioCGU, FlgAtiva) 
	VALUES (2, 'Escala Brasil Transparente', 3, 0, 5, 0, 0, 
	0, 1, 1);
	
-- Questionario
INSERT INTO dbo.Questionario (IdQuestionario, TxtEstrutura)
	VALUES (0, '');

-- Respostas
INSERT INTO dbo.Resposta (IdResposta, IdQuestionario, IdAvaliacao, TxtEstrutura, IdEtapa, IdUsuario)
	VALUES (0, 0, 0, '', 0, 0);	
	
-- Resultados de avaliações
INSERT INTO dbo.ResultadoAvaliacao(IdResultadoAvaliacao, IdAvaliacao, SigUF, NomMunicipio, ValNota)
	VALUES (0, 0, 'DF', 'Brasília', 10);

INSERT INTO dbo.ResultadoAvaliacao(IdResultadoAvaliacao, IdAvaliacao, SigUF, NomMunicipio, ValNota)
	VALUES (1, 0, 'SP', 'São Paulo', 9);	
	
-- Entidades Avaliadoras
INSERT INTO dbo.EntidadeAvaliadora (IdEntidadeAvaliadora, IdTipoEntidade, NomEntidade, FlgCGU) VALUES (1, 1, 'Entidade Avaliadora 1', 0);
INSERT INTO dbo.EntidadeAvaliadora (IdEntidadeAvaliadora, IdTipoEntidade, NomEntidade, FlgCGU) VALUES (2, 1, 'Entidade Avaliadora 2', 0);
INSERT INTO dbo.EntidadeAvaliadora (IdEntidadeAvaliadora, IdTipoEntidade, NomEntidade, FlgCGU) VALUES (3, 1, 'Entidade Avaliadora 3', 0);
INSERT INTO dbo.EntidadeAvaliadora (IdEntidadeAvaliadora, IdTipoEntidade, NomEntidade, FlgCGU) VALUES (4, 1, 'Entidade Avaliadora 4', 0);
INSERT INTO dbo.EntidadeAvaliadora (IdEntidadeAvaliadora, IdTipoEntidade, NomEntidade, FlgCGU) VALUES (5, 1, 'Entidade Avaliadora 5', 0);

INSERT INTO dbo.EntidadeAvaliadora_Usuario(IdEntidadeAvaliadoraUsuario, IdEntidadeAvaliadora, IdUsuario, FlgAdministrador)
	VALUES (0, 0, 0, 1);
INSERT INTO dbo.EntidadeAvaliadora_Usuario(IdEntidadeAvaliadoraUsuario, IdEntidadeAvaliadora, IdUsuario, FlgAdministrador)
	VALUES (1, 0, 0, 0);
	
-- Entidades Avaliadas
INSERT INTO dbo.EntidadeAvaliada (IdEntidadeAvaliada, IdTipoEntidade, IdPoder) VALUES (0, 0, 0);
INSERT INTO dbo.EntidadeAvaliada (IdEntidadeAvaliada, IdTipoEntidade, IdPoder) VALUES (1, 0, 0);

-- Localidade
INSERT INTO dbo.Localidade (IdLocalidade, IdEntidadeAvaliada) VALUES (0, 0);
INSERT INTO dbo.Localidade (IdLocalidade, IdEntidadeAvaliada) VALUES (1, 1);
	
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

-- Município (ToDO: outros)

INSERT INTO dbo.Vw_Municipio (IdMunicipio, SigUF, DescMunicipio, CodMunicipioGeo)
	VALUES (530010,	'DF',	'Brasília', 5300108);
	
/*	
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (3, 'Escala Brasil Transparente v4', 4, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (4, 'Escala Brasil Transparente v5', 5, 0, 5, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (5, 'Escala Brasil Transparente v6', 6, 0, 5, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (6, 'Escala Brasil Transparente v7', 7, 0, 5, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (7, 'Escala Brasil Transparente v8', 8, 0, 5, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (8, 'Escala Brasil Transparente v9', 9, 0, 5, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (9, 'Escala Brasil Transparente v10', 10, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (10, 'Escala Brasil Transparente v11', 11, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (11, 'Escala Brasil Transparente v12', 12, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (12, 'Escala Brasil Transparente v13', 13, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (13, 'Escala Brasil Transparente v14', 14, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (14, 'Escala Brasil Transparente v15', 15, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (15, 'Escala Brasil Transparente v16', 16, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (16, 'Escala Brasil Transparente v17', 17, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (17, 'Escala Brasil Transparente v18', 18, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (18, 'Escala Brasil Transparente v19', 19, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (19, 'Escala Brasil Transparente v20', 20, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (20, 'Escala Brasil Transparente v21', 21, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (21, 'Escala Brasil Transparente v22', 22, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (22, 'Escala Brasil Transparente v23', 23, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (23, 'Escala Brasil Transparente v24', 24, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (24, 'Escala Brasil Transparente v25', 25, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (25, 'Escala Brasil Transparente v26', 26, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (26, 'Escala Brasil Transparente v27', 27, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (27, 'Escala Brasil Transparente v28', 28, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (28, 'Escala Brasil Transparente v29', 29, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (29, 'Escala Brasil Transparente v30', 30, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (30, 'Escala Brasil Transparente v31', 31, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (31, 'Escala Brasil Transparente v32', 32, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (32, 'Escala Brasil Transparente v33', 33, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (33, 'Escala Brasil Transparente v34', 34, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (34, 'Escala Brasil Transparente v35', 35, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (35, 'Escala Brasil Transparente v36', 36, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (36, 'Escala Brasil Transparente v37', 37, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (37, 'Escala Brasil Transparente v38', 38, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (38, 'Escala Brasil Transparente v39', 39, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (39, 'Escala Brasil Transparente v40', 40, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (40, 'Escala Brasil Transparente v41', 41, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (41, 'Escala Brasil Transparente v42', 42, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (42, 'Escala Brasil Transparente v43', 43, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (43, 'Escala Brasil Transparente v44', 44, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (44, 'Escala Brasil Transparente v45', 45, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (45, 'Escala Brasil Transparente v46', 46, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (46, 'Escala Brasil Transparente v47', 47, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (47, 'Escala Brasil Transparente v48', 48, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (48, 'Escala Brasil Transparente v49', 49, 1, 3, 0, 0, 1);
INSERT INTO dbo.Avaliacao (IdAvaliacao, NomAvaliacao, NumEdicao, IdTipoAvaliacao, IdFase, idPoder, IdEntidadeAvaliadora, FlgAtiva) values (49, 'Escala Brasil Transparente v50', 50, 0, 5, 0, 0, 1);
*/	
	