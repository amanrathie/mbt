CREATE SCHEMA dbo;

-- Tabelas de tipos

CREATE TABLE dbo.TipoAvaliacao
(
  IdTipoAvaliacao        INTEGER IDENTITY NOT NULL,
  DescTipoAvaliacao      VARCHAR(255)    NOT NULL,

  CONSTRAINT PK_TipoAvaliacao PRIMARY KEY (IdTipoAvaliacao)
);

CREATE TABLE dbo.TipoQuestao
(
  IdTipoQuestao        	INTEGER IDENTITY NOT NULL,
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

CREATE TABLE dbo.Avaliacao
(
  IdAvaliacao       	INTEGER IDENTITY NOT NULL,
  NomAvaliacao      	VARCHAR(255)    NOT NULL,
  NumEdicao         	INTEGER,

  CONSTRAINT PK_Avaliacao PRIMARY KEY (IdAvaliacao)
);

CREATE TABLE dbo.AvaliacaoLog
(
  REV             		INTEGER         NOT NULL,
  REVTYPE         		TINYINT,
  IdAvaliacao       	INTEGER  		NOT NULL,
  NomAvaliacao      	VARCHAR(255)    NOT NULL,
  NumEdicao         	INTEGER
);

CREATE TABLE dbo.Bloco (
   IdBloco              INTEGER IDENTITY NOT NULL,
   IdAvaliacao          INTEGER              NULL,
   NomBloco             VARCHAR(255)         NULL,
   DescBloco            VARCHAR(500)         NULL,
   TxtIncentivo         VARCHAR(500)         NULL,
   ValPeso              NUMERIC(4,2)         NULL,
   FlgObrigatorio       BIT                  NULL,
   FlgAtivo             BIT                  NULL,
   NumOrdem             INTEGER              NULL,
   ValSomaPesoQuestoes  NUMERIC(4,2)         NULL,
   
   CONSTRAINT PK_Bloco PRIMARY KEY (IdBloco),
   CONSTRAINT FK_Bloco_Avaliacao FOREIGN KEY (IdAvaliacao) REFERENCES Avaliacao (IdAvaliacao)
);

CREATE TABLE dbo.BlocoLog (
  REV             		INTEGER         NOT NULL,
  REVTYPE         		TINYINT,
   IdBloco              INTEGER  		 NOT NULL,
   IdAvaliacao          INTEGER              NULL,
   NomBloco             VARCHAR(255)         NULL,
   DescBloco            VARCHAR(500)         NULL,
   TxtIncentivo         VARCHAR(500)         NULL,
   ValPeso              NUMERIC(4,2)         NULL,
   FlgObrigatorio       BIT                  NULL,
   FlgAtivo             BIT                  NULL,
   NumOrdem             INTEGER              NULL,
   ValSomaPesoQuestoes  NUMERIC(4,2)         NULL,
);
      
CREATE TABLE dbo.Questao (
   IdQuestao            INTEGER IDENTITY NOT NULL,
   IdTipoQuestao        INTEGER              NULL,
   IdBloco              INTEGER              NULL,
   TxtPergunta          VARCHAR(255)         NULL,
   NumOrdem             INTEGER              NULL,
   FlgObrigatoria 		BIT                  NULL,
   ValPeso              NUMERIC(4,2)         NULL,
   TxtAjuda             VARCHAR(255)         NULL,
   
   CONSTRAINT PK_Questao PRIMARY KEY (IdQuestao),
   CONSTRAINT Fk_Questao_TipoQuestao FOREIGN KEY (IdTipoQuestao) REFERENCES dbo.TipoQuestao (IdTipoQuestao),
   CONSTRAINT FK_Questao_Bloco FOREIGN KEY (IdBloco) REFERENCES dbo.Bloco (IdBloco)
);

CREATE TABLE dbo.QuestaoLog (
   REV             		INTEGER          NOT NULL,
   REVTYPE         		TINYINT,
   IdQuestao            INTEGER  		 NOT NULL,
   IdTipoQuestao        INTEGER              NULL,
   IdBloco              INTEGER              NULL,
   TxtPergunta          VARCHAR(255)         NULL,
   NumOrdem             INTEGER              NULL,
   FlgObrigatoria 		BIT                  NULL,
   ValPeso              NUMERIC(4,2)         NULL,
   TxtAjuda             VARCHAR(255)         NULL,
);

-- Envers
CREATE TABLE dbo.Revisao
(
  id         INT IDENTITY NOT NULL,
  dthRevisao DATETIME     NOT NULL,
  idUsuario  INT,

  CONSTRAINT PK_Revisao PRIMARY KEY (id)
);
