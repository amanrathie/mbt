CREATE SCHEMA dbo;

SET IGNORECASE true;
SET MODE MSSQLServer;

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

  CONSTRAINT PK_Fase PRIMARY KEY (IdFase)
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

CREATE TABLE dbo.EntidadeAvaliadora (
   IdEntidadeAvaliadora           INTEGER IDENTITY NOT NULL,
   IdTipoEntidade				  INTEGER        NOT NULL,
   NomEntidade					  VARCHAR(255)   NOT NULL,
   FlgCGU						  TINYINT		 NOT NULL,
   
   CONSTRAINT PK_EntidadeAvaliadora PRIMARY KEY (IdEntidadeAvaliadora),
   CONSTRAINT FK_EntAvaliadora_TipoEntidade FOREIGN KEY (IdTipoEntidade) REFERENCES dbo.TipoEntidade (IdTipoEntidade)
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
  NumEdicao         	INTEGER,
  IdTipoAvaliacao		INTEGER			NOT NULL,
  IdFase				INTEGER			NOT NULL,
  IdQuestionario		INTEGER,
  IdEntidadeAvaliadora	INTEGER,

  CONSTRAINT PK_Avaliacao PRIMARY KEY (IdAvaliacao),
  CONSTRAINT FK_Avaliacao_TipoAvaliacao FOREIGN KEY (IdTipoAvaliacao) REFERENCES dbo.TipoAvaliacao (IdTipoAvaliacao),
  CONSTRAINT FK_Avaliacao_Fase FOREIGN KEY (IdFase) REFERENCES dbo.Fase (IdFase),
  CONSTRAINT FK_Avaliacao_Questionario FOREIGN KEY (IdQuestionario) REFERENCES dbo.Questionario (IdQuestionario),
  CONSTRAINT FK_Avaliacao_EntAvaliadora FOREIGN KEY (IdEntidadeAvaliadora) REFERENCES dbo.EntidadeAvaliadora (IdEntidadeAvaliadora)
);

CREATE TABLE dbo.AvaliacaoLog
(
  REV             		INTEGER         NOT NULL,
  REVTYPE         		TINYINT,
  IdAvaliacao       	INTEGER  		NOT NULL,
  NomAvaliacao      	VARCHAR(255)    NOT NULL,
  NumEdicao         	INTEGER,
  IdTipoAvaliacao		INTEGER			NOT NULL,
  IdFase				INTEGER			NOT NULL,
  IdQuestionario		INTEGER,
  IdEntidadeAvaliadora	INTEGER
);


CREATE TABLE dbo.Resposta (
   IdResposta INTEGER IDENTITY NOT NULL,
   IdQuestionario        	INTEGER              NULL, -- not null
   TxtEstrutura			TEXT        NULL,
   municipio			VARCHAR(100)		 NULL, -- temporario
   
   CONSTRAINT PK_Resposta PRIMARY KEY (IdResposta),
   CONSTRAINT FK_Resposta_Questionario FOREIGN KEY (IdQuestionario) REFERENCES dbo.Questionario (IdQuestionario)
);

CREATE TABLE dbo.RespostaLog (
   REV             		INTEGER         NOT NULL,
   REVTYPE         		TINYINT,
	IdResposta INTEGER  NOT NULL,
   IdQuestionario        	INTEGER              NULL, -- not null
   TxtEstrutura			TEXT        NULL,
   municipio			VARCHAR(100)		 NULL, -- temporario
);

CREATE TABLE dbo.ResultadoAvaliacao (
   IdResultadoAvaliacao INTEGER IDENTITY NOT NULL,
   IdAvaliacao        	INTEGER          NOT NULL,
   NomMunicipio			TEXT        NULL,
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
INSERT INTO dbo.Fase VALUES (0, 'Em planejamento');
INSERT INTO dbo.Fase VALUES (1, 'Questionário em aprovação');
INSERT INTO dbo.Fase VALUES (2, 'Questionário aprovado');
INSERT INTO dbo.Fase VALUES (3, 'Em andamento');
INSERT INTO dbo.Fase VALUES (4, 'Aguardando publicação');
INSERT INTO dbo.Fase VALUES (5, 'Publicada');

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

-- Entidade Avaliadora
INSERT INTO dbo.EntidadeAvaliadora (IdEntidadeAvaliadora, IdTipoEntidade, NomEntidade, FlgCGU) values (0, 1, 'CGU', 1);
