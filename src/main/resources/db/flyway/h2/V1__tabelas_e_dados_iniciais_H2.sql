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

CREATE TABLE dbo.TipoFaseAvaliacao
(
  IdTipoFaseAvaliacao        INTEGER  NOT NULL,
  DescTipoFaseAvaliacao      VARCHAR(255)    NOT NULL,

  CONSTRAINT PK_TipoFaseAvaliacao PRIMARY KEY (IdTipoFaseAvaliacao)
);

CREATE TABLE dbo.TipoQuestao
(
  IdTipoQuestao        	INTEGER  NOT NULL,
  DescTipoQuestao      	VARCHAR(255)    NOT NULL,

  CONSTRAINT PK_TipoQuestao PRIMARY KEY (IdTipoQuestao)
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
  IdTipoFaseAvaliacao	INTEGER			NOT NULL,
  IdQuestionario		INTEGER,

  CONSTRAINT PK_Avaliacao PRIMARY KEY (IdAvaliacao),
  CONSTRAINT FK_Avaliacao_TipoAvaliacao FOREIGN KEY (IdTipoAvaliacao) REFERENCES TipoAvaliacao (IdTipoAvaliacao),
  CONSTRAINT FK_Avaliacao_TipoFaseAv FOREIGN KEY (IdTipoFaseAvaliacao) REFERENCES TipoFaseAvaliacao (IdTipoFaseAvaliacao),
  CONSTRAINT FK_Avaliacao_Questionario FOREIGN KEY (IdQuestionario) REFERENCES dbo.Questionario (IdQuestionario)
);

CREATE TABLE dbo.AvaliacaoLog
(
  REV             		INTEGER         NOT NULL,
  REVTYPE         		TINYINT,
  IdAvaliacao       	INTEGER  		NOT NULL,
  NomAvaliacao      	VARCHAR(255)    NOT NULL,
  NumEdicao         	INTEGER,
  IdTipoAvaliacao		INTEGER			NOT NULL,
  IdTipoFaseAvaliacao	INTEGER			NOT NULL,
  IdQuestionario		INTEGER,
);


CREATE TABLE dbo.RespostaQuestionario (
   IdRespostaQuestionario INTEGER IDENTITY NOT NULL,
   IdQuestionario        	INTEGER              NULL, -- not null
   TxtEstrutura			TEXT        NULL,
   municipio			VARCHAR(100)		 NULL, -- temporario
   
   CONSTRAINT PK_RespostaQuestionario PRIMARY KEY (IdRespostaQuestionario),
   CONSTRAINT FK_RespostaQuest_Quest FOREIGN KEY (IdQuestionario) REFERENCES dbo.Questionario (IdQuestionario)
);

CREATE TABLE dbo.RespostaQuestionarioLog (
   REV             		INTEGER         NOT NULL,
   REVTYPE         		TINYINT,
   IdRespostaQuestionario INTEGER  NOT NULL,
   IdQuestionario        	INTEGER              NULL, -- not null
   TxtEstrutura			TEXT        NULL,
   municipio			VARCHAR(100)		 NULL, -- temporario
);

CREATE TABLE dbo.ResultadoAvaliacao (
   IdResultadoAvaliacao INTEGER IDENTITY NOT NULL,
   IdAvaliacao        	INTEGER          NOT NULL,
   NomLocalidade			TEXT        NULL,
   
   CONSTRAINT PK_ResultadoAvaliacao PRIMARY KEY (IdResultadoAvaliacao),
   CONSTRAINT FK_ResultadoAvaliacao_Av FOREIGN KEY (IdAvaliacao) REFERENCES dbo.Avaliacao (IdAvaliacao)
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
INSERT INTO dbo.TipoFaseAvaliacao VALUES (0, 'Em planejamento');
INSERT INTO dbo.TipoFaseAvaliacao VALUES (1, 'Questionário em aprovação');
INSERT INTO dbo.TipoFaseAvaliacao VALUES (2, 'Questionário aprovado');
INSERT INTO dbo.TipoFaseAvaliacao VALUES (3, 'Em andamento');
INSERT INTO dbo.TipoFaseAvaliacao VALUES (4, 'Aguardando publicação');
INSERT INTO dbo.TipoFaseAvaliacao VALUES (5, 'Publicada');

-- Tipo Questão
INSERT INTO dbo.TipoQuestao VALUES (0, 'Descritiva');
INSERT INTO dbo.TipoQuestao VALUES (1, 'Múltipla Escolha');
INSERT INTO dbo.TipoQuestao VALUES (2, 'Escala');
INSERT INTO dbo.TipoQuestao VALUES (3, 'Matriz');

-- Usuario/Perfil
INSERT INTO dbo.Perfil VALUES (0, 'Administrador CGU');
INSERT INTO dbo.Perfil VALUES (1, 'Administrador de Entidade');
