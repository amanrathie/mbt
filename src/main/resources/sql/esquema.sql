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

CREATE TABLE dbo.Avaliacao
(
  IdAvaliacao       	INTEGER IDENTITY NOT NULL,
  NomAvaliacao      	VARCHAR(255)    NOT NULL,
  NumEdicao         	INTEGER,

  CONSTRAINT PK_Avaliacao PRIMARY KEY (IdAvaliacao)
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
