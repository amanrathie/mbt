CREATE TABLE dbo.RiscoLog
(
  REV            INT NOT NULL,
  REVTYPE        TINYINT,
  IdRisco        INT,
  DescEvento     VARCHAR (300),
  FlgAtivo       BIT
);

CREATE TABLE dbo.CausaLog
(
  REV            INT NOT NULL,
  REVTYPE        TINYINT,
  IdCausa        INT,
  DescCausa      VARCHAR (300)
);

CREATE TABLE dbo.ConsequenciaLog
(
  REV                   INT NOT NULL,
  REVTYPE               TINYINT,
  IdConsequencia        INT,
  DescConsequencia      VARCHAR (300)
);

CREATE TABLE dbo.RiscoCausaLog
(
  REV         INT NOT NULL,
  REVTYPE     TINYINT,
  IdRisco     INT,
  IdCausa     INT
);

CREATE TABLE dbo.RiscoConsequenciaLog
(
  REV                INT NOT NULL,
  REVTYPE            TINYINT,
  IdRisco            INT,
  IdConsequencia     INT
);