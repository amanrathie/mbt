INSERT INTO dbo.Usuario
(IdUsuario, NomUsuario,                   CpfUsuario,     SiapeUsuario, DescLogin,                      EmlUsuario,                     TelUsuario,       ChaveApi,                           DthUltimoLogin,         FlgAtivo,   FreqRecebEmail)
VALUES
(1, 	      'Renan',                      '00000000001',  '01455691',   'CGU\renanlf',                  'email@cgu.gov.br',             '(61) 2222-2222', null,                               '2030-12-31 23:59:59',  1,          1),
(9, 	      'Fulano Desativado',          '00000000009',  null,         'CGU\fulanodesativado',         'fulano.desativado@cgu.gov.br', '(61) 2222-2222', null,                               '2030-12-31 23:59:59',  0,          1),
-- Esses usuarios devem ser usados nos testes de integração, pra testar os respectivos perfis.
-- Em caso de problemas (como expiração de senha), falar com o Tim. Ticket de criação: RF00003157
(11, 	      'Teste ADMIN 1',              '10000000001',  '12345678',   'CGU\USER_CGSIS_TESTE_01', 	    'cosis@cgu.gov.br',             '(61) 2222-2222',	'0fadfb8fd4f7cc48f04eafa048c32635', '2030-12-31 23:59:59',  1,          1),
(12, 	      'Teste CGSIS 2',              '10000000002',  null, 				'CGU\USER_CGSIS_TESTE_02', 	    'cosis@cgu.gov.br',             '(61) 2222-2222',	null,                               '2030-12-31 23:59:59',  1,          1),
(13, 	      'Teste CGSIS 3',              '10000000003',  null, 				'CGU\USER_CGSIS_TESTE_03', 	    'cosis@cgu.gov.br',             '(61) 2222-2222',	null,                               '2030-12-31 23:59:59',  1,          0),
(14, 	      'Teste CGSIS 4',              '10000000004',  null, 				'CGU\USER_CGSIS_TESTE_04', 	    'cosis@cgu.gov.br',             '(61) 2222-2222',	null,                               '2030-12-31 23:59:59',  1,          1),
(15, 	      'Teste CGSIS 5',              '10000000005',  null, 				'CGU\USER_CGSIS_TESTE_05', 	    'cosis@cgu.gov.br',             '(61) 2222-2222',	null,                               '2030-12-31 23:59:59',  1,          1),
(16, 	      'Teste CGSIS 6',              '10000000006',  null, 				'CGU\USER_CGSIS_TESTE_06', 	    'cosis@cgu.gov.br',             '(61) 2222-2222',	null,                               '2030-12-31 23:59:59',  1,          1),
(17, 	      'Teste CGSIS 7',              '10000000007',  null, 				'CGU\USER_CGSIS_TESTE_07', 	    'cosis@cgu.gov.br',             '(61) 2222-2222',	null,                               '2030-12-31 23:59:59',  1,          1),
(18, 	      'Teste CGSIS 8',              '10000000008',  null, 				'CGU\USER_CGSIS_TESTE_08', 	    'cosis@cgu.gov.br',             '(61) 2222-2222',	null,                               '2030-12-31 23:59:59',  1,          1),
(19, 	      'Teste CGSIS 9',              '10000000009',  null, 				'CGU\USER_CGSIS_TESTE_09', 	    'cosis@cgu.gov.br',             '(61) 2222-2222',	null,                               '2030-12-31 23:59:59',  1,          1),
(20, 	      'Teste CGSIS 10 (expirado)',  '10000000010',  null, 				'CGU\USER_CGSIS_TESTE_10', 	    'cosis@cgu.gov.br',             '(61) 2222-2222',	null,                               '2017-01-01 10:00:00',  1,          1),
(24, 	      'Teste CGTEC 1',              '10000000013',  null, 				'CGU\USER_CGTEC_TESTE_01', 	    'cgtec@cgu.gov.br',             '(61) 2222-2222',	null,                               '2030-12-31 23:59:59',  1,          0),
(27, 	      'SISTEMA ACESSO',             '10000000014',  null, 				'CGU\sistema_acesso', 	        'cosis@cgu.gov.br',             '(61) 2222-2222',	'48649b9b64ceab783f5104221b219fcd', '2030-12-31 23:59:59',  1,          0),
(28, 	      'Pimenta',                    '10000000015',  null, 				'CGU\gabrielcp',       	        'cosis@cgu.gov.br',             '(61) 2222-2222',	null ,                              '2030-12-31 23:59:59',  1,          0);

INSERT INTO dbo.Perfil
(IdPerfil, FlgOferecidoPublicoExterno, DescPerfil)
VALUES
  (0,      false,                      'Desenvolvedor DTI'),
  (1,      false,                      'Gestor Sistema'),
  (2,      false,                      'Auditor CGU'),
  (3,      true,                       'Gestor de UPAG');

INSERT INTO dbo.PermissoesDoPerfil
(IdPerfil, IdPermissao)
    VALUES
(  0,  'GERENCIAR_TODAS_INSTANCIAS'),       (  1,  'GERENCIAR_TODAS_INSTANCIAS'),
(  0,  'MENU_ADMINISTRACAO'),               (  1,  'MENU_ADMINISTRACAO'),
(  0,  'MENU_AUDITORIA_CONTINUA'),          (  1,  'MENU_AUDITORIA_CONTINUA'),
(  0,  'MENU_PLANEJAMENTO'),                (  1,  'MENU_PLANEJAMENTO'),
(  0,  'CONSULTAR_USUARIOS'),               (  1,  'CONSULTAR_USUARIOS'),
(  0,  'GERENCIAR_USUARIOS'),               (  1,  'GERENCIAR_USUARIOS'),
(  0,  'CONSULTAR_UNIDADES'),               (  1,  'CONSULTAR_UNIDADES'),
(  0,  'GERENCIAR_UNIDADES'),               (  1,  'GERENCIAR_UNIDADES'),
(  0,  'CONSULTAR_TRILHAS'),                (  1,  'CONSULTAR_TRILHAS'),
(  0,  'GERENCIAR_TRILHAS'),                (  1,  'GERENCIAR_TRILHAS'),
(  0,  'GERENCIAR_CAMPOS'),                 (  1,  'GERENCIAR_CAMPOS'),
(  0,  'GERENCIAR_SUBCLASSES'),             (  1,  'GERENCIAR_SUBCLASSES'),
(  0,  'CONSULTAR_RISCOS'),                 (  1,  'CONSULTAR_RISCOS'),
(  0,  'GERENCIAR_RISCOS'),                 (  1,  'GERENCIAR_RISCOS'),
(  0,  'GERENCIAR_AREAS_DE_CONHECIMENTO'),  (  1,  'GERENCIAR_AREAS_DE_CONHECIMENTO'),
(  0,  'GERENCIAR_AVISOS'),                 (  1,  'GERENCIAR_AVISOS'),
(  2,  'MENU_AUDITORIA_CONTINUA'),
(  2,  'MENU_PLANEJAMENTO'),
(  2,  'CONSULTAR_USUARIOS'),
(  2,  'CONSULTAR_UNIDADES'),
(  2,  'CONSULTAR_TRILHAS'),
(  2,  'CONSULTAR_RISCOS'),
(  3,  'MENU_AUDITORIA_CONTINUA');

INSERT INTO dbo.PerfisDoUsuario
(IdUsuario, IdPerfil)
VALUES
(1,  0),
(1,  1),
(1,  2),
(11, 0),
(11, 1),
(12, 1),
(24, 2),
(27, 2),
(28, 0);

insert into dbo.TipoOrgao
(IdTipoOrgao, NomTipoOrgao)
VALUES
(0,           'Administração Direta'),
(1,           'Administração Direta Estadual'),
(2,           'Administração Direta Municipal'),
(3,           'Administração Indireta Estadual'),
(4,           'Administração Indireta Municipal'),
(5,           'Autarquia'),
(6,           'Fundação'),
(7,           'Empresa Pública e Comercial Financeira'),
(8,           'Economia Mista'),
(9,           'Fundos'),
(10,          'Empresa Pública Industrial e Agrícola'),
(11,          'Empresa Privada'),
(12,          'Organização Social'),
(13,          'Serviço Social Autônomo');

insert into dbo.Orgao
(IdOrgao, IdTipoOrgao, NomOrgao,                        SigOrgao, CodOrgao, FlgAtivo)
VALUES
(1,       1,           'Presidência da República',      'PR',     '100000', 1),
(2,       1,           'Controladoria-Geral da União',  'CGU',    '100001', 1),
(3,       3,           'Qualquer orgao inativo',        'QOI',    '500001', 0),
(4,       0,           'Senado Federal',                'SF',     '500002', 1),
(5,       0,           'Câmara dos Deputados',          'CD',     '500003', 1),
(6,       0,           'Supremo Tribunal Federal',      'STF',    '500004', 1),
(7,       0,           'Tribunal Superior Eleitoral',   'TSE',    '500005', 1);

INSERT INTO dbo.UF
(SigUF, DescUF)
VALUES
('AC',  'Acre'),
('AL',  'Alagoas'),
('AM',  'Amazonas'),
('AP',  'Amapá'),
('BA',  'Bahia'),
('CE',  'Ceará'),
('DF',  'Distrito Federal'),
('ES',  'Espírito Santo'),
('GO',  'Goiás'),
('MA',  'Maranhão'),
('MG',  'Minas Gerais'),
('MS',  'Mato Grosso do Sul'),
('MT',  'Mato Grosso'),
('PA',  'Pará'),
('PB',  'Paraíba'),
('PE',  'Pernambuco'),
('PI',  'Piauí'),
('PR',  'Paraná'),
('RJ',  'Rio de Janeiro'),
('RN',  'Rio Grande do Norte'),
('RO',  'Rondônia'),
('RR',  'Roraima'),
('RS',  'Rio Grande do Sul'),
('SC',  'Santa Catarina'),
('SE',  'Sergipe'),
('SP',  'São Paulo'),
('TO',  'Tocantins');

INSERT INTO dbo.Municipio
(IdMunicipio, DescMunicipio,          SigUF)
VALUES
(1,           'Brasília',             'DF'),
(2,           'Belo Horizonte',       'MG'),
(3,           'Pirenópolis',          'GO'),
(4,           'Goiânia',              'GO'),
(5,           'Rio de Janeiro',       'RJ'),
(6,           'São Paulo',            'SP'),
(7,           'Salvador',             'BA'),
(8,           'Fortaleza',            'CE'),
(9,           'Piracanjeba',          'GO'),
(10,          'Natal',                'RN'),
(11,          'Mara Rosa',            'TO');

insert into dbo.TipoUnidade
(IdTipoUnidade, NomTipoUnidade)
VALUES
  (0, 'Unidade de Auditoria Interna Governamental'),
  (1, 'Unidade Auditada'),
  (2, 'Unidade Pagadora');

INSERT INTO dbo.Unidade
(IdUnidade, IdTipoUnidade, NomUnidade,                                                      SigUnidade,           CodUnidade, FlgAtivo,  IdUnidadeSuperior, TelUnidade,        EmlUnidade,                IdMunicipio, IdOrgao, DescHierarquia)
VALUES
  (1,       1,             'Ministerio X',                                                  'MX',                 '200001',   1,         null,              '(61) 2222-2222',  'email@unidade.com.br',    1,           1,       '|1|'),
  (2,       2,             'UPAG Y',                                                        'UPAGY',              '200002',   1,         null,              '(61) 2222-2222',  'email@unidade.com.br',    2,           1,       '|2|'),
  (3,       0,             'Diretoria de TI ',                                              'DTI',                '200003',   1,         null,              '(61) 2222-2222',  'email@unidade.com.br',    3,           2,       '|3|'),
  (4,       0,             'Diretoria de TI old',                                           'DSI',                '200003',   0,         null,              '(61) 2222-2222',  'email@unidade.com.br',    4,           2,       '|4|'),
  (5,       0,             'DTI/CGTEC - Coordenação-Geral de Infra-Estrutura Tecnológica',  'DTI/CGTEC',          '200004',   1,         3,                 '(61) 2222-2222',  'email@unidade.com.br',    5,           2,       '|5|3|'),
  (6,       0,             'DTI/CGSIS - Coordenação-Geral de Sistemas de Informação',       'DTI/CGSIS',          '200005',   1,         3,                 '(61) 2222-2222',  'email@unidade.com.br',    6,           2,       '|6|3|'),
  (7,       0,             'DTI/CGSIS/Serviço de Controle Interno',                         'DTI/CGSIS/COSIS',    '200006',   1,         6,                 '(61) 2222-2222',  'email@unidade.com.br',    7,           2,       '|7|6|3|');

INSERT INTO dbo.LotacoesDoUsuario
(IdUsuario, IdUnidade)
VALUES
(1,         3),
(11,        1),
(11,        3),
(11,        6),
(18,        2),
(19,        6),
(20,        7),
(24,        5);

INSERT INTO dbo.Configuracao
(id, valor)
VALUES
  ('ParametroDesconhecido', 'Para testar se o warning está funcionando quando vem um parametro desconhecido');


------------------------- TABELAS DE LOG
-- tabela de LOG
INSERT INTO dbo.UsuarioLog
(REV, 	REVTYPE, 	IdUsuario, 	NomUsuario, 				      CpfUsuario,     SiapeUsuario, 	DescLogin,              EmlUsuario, 					          TelUsuario, 		  ChaveApi, 								          FlgAtivo, FreqRecebEmail)
VALUES
(0, 	  1, 			  1, 			    'SER_CGSIS_TESTE_01',     '10000000001',  '012345678',   	'SER_CGSIS_TESTE_01',  'email@cgu.gov.br',           '(61) 2020-6925',  '0fadfb8fd4f7cc48f04eafa048c32635',   1,        1);

INSERT INTO  dbo.PerfisDoUsuarioLog
(REV,	REVTYPE,	IdPerfil,	IdUsuario)
VALUES
(1,		1,			1,			2);

INSERT INTO dbo.UnidadeLog
(REV, REVTYPE,	IdUnidade,	IdOrgao,	IdTipoUnidade,	NomUnidade,				SigUnidade,		CodUnidade, 	FlgAtivo,	idUnidadeSuperior,	TelUnidade,			  EmlUnidade,						  IdMunicipio)
VALUES
(1, 	1,       	1,          1, 			  1,				      'Ministerio X',   'MX',       	'200001',     1,    		null,          		  '(61) 2222-2222', 'email@unidade.com.br', 1);

INSERT INTO dbo.LotacoesDoUsuarioLog
(REV,	REVTYPE,	IdUnidade,	IdUsuario)
VALUES
(1,		1,			1,			1);

INSERT INTO dbo.GestoresDaUnidade
(IdUnidade, IdUsuario)
VALUES
(7,         11),
(7,         12);

