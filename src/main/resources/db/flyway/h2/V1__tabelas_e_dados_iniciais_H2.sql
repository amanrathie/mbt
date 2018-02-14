-- Tabelas de tipos

CREATE TABLE dbo.TipoAvaliacao
(
  IdTipoAvaliacao        INTEGER  NOT NULL,
  DescTipoAvaliacao      VARCHAR(255)    NOT NULL,

  CONSTRAINT PK_TipoAvaliacao PRIMARY KEY (IdTipoAvaliacao)
);

CREATE TABLE dbo.Fase
(
  IdFase        INTEGER  NOT NULL,
  DescFase      VARCHAR(255)    NOT NULL,
  NumOrdem      INTEGER    NOT NULL,

  CONSTRAINT PK_Fase PRIMARY KEY (IdFase)
);


CREATE TABLE dbo.Poder
(
  IdPoder        	INTEGER  NOT NULL,
  DescPoder      	VARCHAR(255)    NOT NULL,

  CONSTRAINT PK_Poder PRIMARY KEY (IdPoder)
);

CREATE TABLE dbo.TipoQuestao
(
  IdTipoQuestao        	INTEGER  NOT NULL,
  DescTipoQuestao      	VARCHAR(255)    NOT NULL,

  CONSTRAINT PK_TipoQuestao PRIMARY KEY (IdTipoQuestao)
);

CREATE TABLE dbo.TipoEntidade
(
  IdTipoEntidade        	INTEGER  NOT NULL,
  DescTipoEntidade      	VARCHAR(255)    NOT NULL,

  CONSTRAINT PK_TipoEntidade PRIMARY KEY (IdTipoEntidade)
);

-- Tabelas de negócio

CREATE TABLE dbo.Perfil
(
  IdPerfil                    INTEGER IDENTITY NOT NULL,
  DescPerfil                  VARCHAR(500)     NOT NULL,

  CONSTRAINT PK_Perfil PRIMARY KEY (IdPerfil)
);

CREATE TABLE dbo.Usuario
(
  IdUsuario       INTEGER IDENTITY NOT NULL,
  IdPerfil		  INTEGER, 		   
  NomUsuario      VARCHAR(1000)    NOT NULL,
  EmlUsuario      VARCHAR(200),

  CONSTRAINT PK_Usuario PRIMARY KEY (IdUsuario),
  CONSTRAINT FK_Usuario_Perfil FOREIGN KEY (IdPerfil) REFERENCES dbo.Perfil (IdPerfil)
);

CREATE TABLE dbo.EntidadeAvaliada (
   IdEntidadeAvaliada           INTEGER IDENTITY NOT NULL,
   IdTipoEntidade				  INTEGER        NOT NULL,
   IdPoder				INTEGER			NOT NULL,
   
   CONSTRAINT PK_EntidadeAvaliada PRIMARY KEY (IdEntidadeAvaliada),
   CONSTRAINT FK_EntAvaliada_TipoEntidade FOREIGN KEY (IdTipoEntidade) REFERENCES dbo.TipoEntidade (IdTipoEntidade),
   CONSTRAINT FK_EntidadeAvaliada_Poder FOREIGN KEY (IdPoder) REFERENCES dbo.Poder (IdPoder),
);


CREATE TABLE dbo.Localidade (
   IdLocalidade           INTEGER IDENTITY NOT NULL,
   IdEntidadeAvaliada           INTEGER  NOT NULL,
   
   CONSTRAINT PK_Localidade PRIMARY KEY (IdLocalidade),
   CONSTRAINT FK_Localidade_EntAvaliada FOREIGN KEY (IdEntidadeAvaliada) REFERENCES dbo.EntidadeAvaliada (IdEntidadeAvaliada)
);

CREATE TABLE dbo.EntidadeAvaliadora (
   IdEntidadeAvaliadora           INTEGER IDENTITY NOT NULL,
   IdTipoEntidade				  INTEGER        NOT NULL,
   NomEntidade					  VARCHAR(255)   NOT NULL,
   FlgCGU						  TINYINT		 NOT NULL,
   
   CONSTRAINT PK_EntidadeAvaliadora PRIMARY KEY (IdEntidadeAvaliadora),
   CONSTRAINT FK_EntAvaliadora_TipoEntidade FOREIGN KEY (IdTipoEntidade) REFERENCES dbo.TipoEntidade (IdTipoEntidade)
);

CREATE TABLE dbo.EntidadeAvaliadora_Usuario (
   IdEntidadeAvaliadoraUsuario           INTEGER IDENTITY NOT NULL,
   IdEntidadeAvaliadora				  INTEGER        NOT NULL,
   IdUsuario					  INTEGER        NOT NULL,
   FlgAdministrador						  TINYINT,
   
   CONSTRAINT PK_EntidadeAvaliadoraUsuario PRIMARY KEY (IdEntidadeAvaliadoraUsuario),
   CONSTRAINT FK_EntAvdoraUsu_EntAvdora FOREIGN KEY (IdEntidadeAvaliadora) REFERENCES dbo.EntidadeAvaliadora (IdEntidadeAvaliadora),
   CONSTRAINT FK_Usuario_EntidadeAvaliadora FOREIGN KEY (IdUsuario) REFERENCES dbo.Usuario (IdUsuario)
);

CREATE TABLE dbo.Questionario (
   IdQuestionario           INTEGER IDENTITY NOT NULL,
   TxtEstrutura				TEXT        NULL,
   
   CONSTRAINT PK_Questionario PRIMARY KEY (IdQuestionario)
);

CREATE TABLE dbo.QuestionarioLog (
   REV             		INTEGER         	 NOT NULL,
   REVTYPE         		TINYINT,
   IdQuestionario           INTEGER 	 NOT NULL,
   TxtEstrutura				TEXT        NULL,
);

CREATE TABLE dbo.Avaliacao
(
  IdAvaliacao       	INTEGER IDENTITY NOT NULL,
  NomAvaliacao      	VARCHAR(255)    NOT NULL,
  DescAvaliacao			VARCHAR(1000),
  IdTipoAvaliacao		INTEGER			NOT NULL,
  IdFase				INTEGER			NOT NULL,
  IdPoder				INTEGER			NOT NULL,
  IdEntidadeAvaliadora	INTEGER			NOT NULL,
  IdUsuarioAdministrador INTEGER		NOT NULL,
  IdUsuarioCGU 			INTEGER			NOT NULL,
  IdQuestionario		INTEGER,
  NumEdicao				INTEGER,
  DescEdicao			VARCHAR(1000),
  DthInicio				DATETIME,
  DthFim				DATETIME,
  FlgAtiva				BIT			NOT NULL,

  CONSTRAINT PK_Avaliacao PRIMARY KEY (IdAvaliacao),
  CONSTRAINT FK_Avaliacao_TipoAvaliacao FOREIGN KEY (IdTipoAvaliacao) REFERENCES dbo.TipoAvaliacao (IdTipoAvaliacao),
  CONSTRAINT FK_Avaliacao_Fase FOREIGN KEY (IdFase) REFERENCES dbo.Fase (IdFase),
  CONSTRAINT FK_Avaliacao_Poder FOREIGN KEY (IdPoder) REFERENCES dbo.Poder (IdPoder),
  CONSTRAINT FK_Avaliacao_EntAvaliadora FOREIGN KEY (IdEntidadeAvaliadora) REFERENCES dbo.EntidadeAvaliadora (IdEntidadeAvaliadora),
  CONSTRAINT FK_Avaliacao_Usuario_1 FOREIGN KEY (IdUsuarioAdministrador) REFERENCES dbo.Usuario (IdUsuario),
  CONSTRAINT FK_Avaliacao_Usuario_2 FOREIGN KEY (IdUsuarioCGU) REFERENCES dbo.Usuario (IdUsuario),
  CONSTRAINT FK_Avaliacao_Questionario FOREIGN KEY (IdQuestionario) REFERENCES dbo.Questionario (IdQuestionario)
);

CREATE TABLE dbo.AvaliacaoLog
(
  REV             		INTEGER         NOT NULL,
  REVTYPE         		TINYINT,
  IdAvaliacao       	INTEGER  		NOT NULL,
  NomAvaliacao      	VARCHAR(255)    NOT NULL,
  IdFase				INTEGER			NOT NULL,
  IdUsuarioAdministrador INTEGER		NOT NULL,
  IdUsuarioCGU 			INTEGER			NOT NULL,
  DthInicio				DATETIME,
  DthFim				DATETIME,
  FlgAtiva				BIT			NOT NULL,
);


CREATE TABLE dbo.Resposta (
   IdResposta INTEGER IDENTITY NOT NULL,
   IdQuestionario        	INTEGER              NOT NULL,
   IdAvaliacao        	INTEGER          NOT NULL,
   TxtEstrutura			TEXT        NULL,
   municipio			VARCHAR(100)		 NULL, -- temporario
   uf				char(2)		 NULL, -- temporario
   
   CONSTRAINT PK_Resposta PRIMARY KEY (IdResposta),
   CONSTRAINT FK_Resposta_Questionario FOREIGN KEY (IdQuestionario) REFERENCES dbo.Questionario (IdQuestionario),
   CONSTRAINT FK_Resposta_Avaliacao FOREIGN KEY (IdAvaliacao) REFERENCES dbo.Avaliacao (IdAvaliacao)
);

CREATE TABLE dbo.RespostaLog (
   REV             		INTEGER         NOT NULL,
   REVTYPE         		TINYINT,
   IdResposta INTEGER  NOT NULL,
   IdQuestionario        	INTEGER              NOT NULL,
   IdAvaliacao        	INTEGER          NOT NULL,
   TxtEstrutura			TEXT        NULL,
   municipio			VARCHAR(100)		 NULL, -- temporario
   uf			char(2)		 NULL, -- temporario
);

CREATE TABLE dbo.ResultadoAvaliacao (
   IdResultadoAvaliacao INTEGER IDENTITY NOT NULL,
   IdAvaliacao        	INTEGER          NOT NULL,
   NomMunicipio			VARCHAR(100)     NOT NULL,
   SigUF				CHAR(2)     	NOT NULL,
   ValNota				NUMBER(4,2),
   
   CONSTRAINT PK_ResultadoAvaliacao PRIMARY KEY (IdResultadoAvaliacao),
   CONSTRAINT FK_ResultadoAv_Avaliacao FOREIGN KEY (IdAvaliacao) REFERENCES dbo.Avaliacao (IdAvaliacao)
);


-- Envers
CREATE TABLE dbo.Revisao
(
  id         INT IDENTITY NOT NULL,
  dthRevisao DATETIME     NOT NULL,
  idUsuario  INT,

  CONSTRAINT PK_Revisao PRIMARY KEY (id)
);


-- Tipo Avaliação
INSERT INTO dbo.TipoAvaliacao VALUES (0, 'Avaliação Independente');
INSERT INTO dbo.TipoAvaliacao VALUES (1, 'Avaliação Cidadã');
INSERT INTO dbo.TipoAvaliacao VALUES (2, 'Auto Avaliação');

-- Fase Avaliação
INSERT INTO dbo.Fase VALUES (0, 'Em planejamento', 0);
INSERT INTO dbo.Fase VALUES (1, 'Questionário em aprovação', 1);
INSERT INTO dbo.Fase VALUES (2, 'Questionário aprovado', 2);
INSERT INTO dbo.Fase VALUES (3, 'Em andamento', 3);
INSERT INTO dbo.Fase VALUES (4, 'Aguardando publicação', 4);
INSERT INTO dbo.Fase VALUES (5, 'Publicada', 5);

-- Tipo Questão
INSERT INTO dbo.TipoQuestao VALUES (0, 'Descritiva');
INSERT INTO dbo.TipoQuestao VALUES (1, 'Múltipla Escolha');
INSERT INTO dbo.TipoQuestao VALUES (2, 'Escala');
INSERT INTO dbo.TipoQuestao VALUES (3, 'Matriz');

-- Usuario/Perfil
INSERT INTO dbo.Perfil VALUES (0, 'Administrador CGU');
INSERT INTO dbo.Perfil VALUES (1, 'Administrador de Entidade');

-- Tipo Entidade
INSERT INTO dbo.TipoEntidade VALUES(0, 'Localidade');
INSERT INTO dbo.TipoEntidade VALUES(1, 'Orgão/Entidade');
INSERT INTO dbo.TipoEntidade VALUES(2, 'Entidade Governamental');
INSERT INTO dbo.TipoEntidade VALUES(3, 'Organização da sociedade civil');

-- Poder
INSERT INTO dbo.Poder VALUES(0, 'Executivo');
INSERT INTO dbo.Poder VALUES(1, 'Legislativo');
INSERT INTO dbo.Poder VALUES(2, 'Judiciário');

-- Entidade Avaliadora
INSERT INTO dbo.EntidadeAvaliadora (IdEntidadeAvaliadora, IdTipoEntidade, NomEntidade, FlgCGU) values (0, 1, 'CGU', 1);

-- Usuarios (colocar somente os que devem ser migrados oficialmente)
INSERT INTO dbo.Usuario (IdUsuario, IdPerfil, NomUsuario) 
	VALUES (0, 0, 'Fulano da Silva - Adm CGU');
INSERT INTO dbo.Usuario (IdUsuario, IdPerfil, NomUsuario) 
	VALUES (1, 0, 'Beltrano da Silva - Avaliador CGU');
