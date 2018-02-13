CREATE SCHEMA dbo;

SET IGNORECASE true;
SET MODE MSSQLServer;

-- View foram criadas aqui para não misturar com os scripts oficiais
CREATE TABLE dbo.Vw_UF
(
  SigUF        CHAR(2)  NOT NULL,
  DescUF       VARCHAR(255)    NOT NULL,

  CONSTRAINT PK_SigUF PRIMARY KEY (SigUF)
);

CREATE TABLE dbo.Vw_Municipio
(
  IdMunicipio        INTEGER  NOT NULL,
  SigUF				 CHAR(2)	NOT NULL,
  DescMunicipio       VARCHAR(255)    NOT NULL,
  CodMunicipioGeo        INTEGER  NOT NULL,

  CONSTRAINT PK_Municipio PRIMARY KEY (IdMunicipio)
);

RUNSCRIPT FROM 'file:src/main/resources/db/flyway/h2/V1__tabelas_e_dados_iniciais_H2.sql';