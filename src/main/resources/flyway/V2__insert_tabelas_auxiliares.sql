INSERT INTO dbo.Usuario
(NomUsuario,                   CpfUsuario,     SiapeUsuario, DescLogin,                      EmlUsuario,                     TelUsuario,       ChaveApi,                           DthUltimoLogin,         FlgAtivo,   FreqRecebEmail)
VALUES
('Renan Leandro Ferreira',     '02646458126',  null,         'CGU\renanlf',                  'renan.ferreira@cgu.gov.br',    '(61) 2020-6978', null,                               '2030-12-31 23:59:59',  1,          1),
('Gustavo Gomes Teixeira',     '03551432678',  null,         'CGU\ggomest',                  'gustavo.teixeira@cgu.gov.br',  '(61) 2020-6925', '0fadfb8fd4f7cc48f04eafa048c32602', '2030-12-31 23:59:59',  1,          1),
('Iuri de Moura Carneiro',     '60265876168',  null,         'CGU\iurimc',                   'iuri.carneiro@cgu.gov.br',     null,             null,                               '2030-12-31 23:59:59',  1,          2),
('Rodrigo Vilela Fonseca de Souza','00000000004',  null,     'CGU\rodrigovfs',               'rodrigo.v.souza@cgu.gov.br',   null,             null,                               '2030-12-31 23:59:59',  1,          1),
('Vinicius Marques Alves Branco','00000000005',  null,       'CGU\viniciusmab',              'vinicius.branco@cgu.gov.br',   null,             null,                               '2030-12-31 23:59:59',  1,          2),
('Anselmo Júlio da Rocha',     '00000000006',  null,         'CGU\anselmojr',                'anselmo.rocha@cgu.gov.br',     null,             'a51a2147060988aeb0a80a42847b759d', '2030-12-31 23:59:59',  1,          2),
('Fábio Sampaio da Costa',     '79561012120',  '0000001', 	 'CGU\fabiosc', 	               'fabio.costa@cgu.gov.br',       null,	           '0fadfb8fd4f7cc48f04eafa048c32603', '2030-12-31 23:59:59',  1,          1),
('SISTEMA ACESSO',             '10000000014',  null, 				 'CGU\sistema_acesso', 	         'cosis@cgu.gov.br',             null,	           '48649b9b64ceab783f5104221b219fcd', '2030-12-31 23:59:59',  1,          0),
('Márcio Pinto Ávalos',        '56492383168',  null, 				'CGU\mavalos', 		               'marcio.avalos@cgu.gov.br',     null,	           null,                               '2030-12-31 23:59:59',  1,          3),
('Heleno Vieira Borges',       '10000000011',  null, 				'CGU\helenob', 		               'heleno.borges@cgu.gov.br',     null,	           null,                               '2030-12-31 23:59:59',  1,          2),
('Daniel Wu',                  '67857338982',  null, 				'CGU\danielyhw', 		            'daniel.wu@cgu.gov.br',         '(61) 2222-2222',	null,                               '2030-12-31 23:59:59',  1,          3);

set identity_insert dbo.perfil on;
INSERT INTO dbo.Perfil
(IdPerfil, FlgOferecidoPublicoExterno, DescPerfil)
VALUES
  (0,      0,                      'Administrador'),
  (1,      0,                      'Consulta de Usuários'),
  (2,      0,                      'Gerenciar Usuários'),
  (3,      0,                      'Consulta de Unidades'),
  (4,      0,                      'Gerenciar Unidades'),
  (5,      0,                      'Gerenciar Trilhas'),
  (6,      0,                      'Gerenciar Campos'),
  (7,      0,                      'Gerenciar Subclasses'),
  (8,      1,                       'Supervisor de Unidade');
set identity_insert dbo.perfil off;


INSERT INTO dbo.PerfisDoUsuario
(IdUsuario, IdPerfil)
select idUsuario, 0 from dbo.Usuario;

set identity_insert dbo.tipoOrgao on;
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
set identity_insert dbo.tipoOrgao off;

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
