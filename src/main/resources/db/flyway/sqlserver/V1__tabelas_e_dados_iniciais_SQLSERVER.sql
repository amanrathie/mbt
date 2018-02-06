-- Script equivalente do SQL Server com os mesmos dados do H2
CREATE TABLE dbo.TipoAvaliacao
(
  IdTipoAvaliacao        INTEGER  NOT NULL,
  DescTipoAvaliacao      VARCHAR(255)    NOT NULL,

  CONSTRAINT PK_TipoAvaliacao PRIMARY KEY (IdTipoAvaliacao)
);