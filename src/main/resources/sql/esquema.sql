CREATE SCHEMA dbo;

CREATE TABLE dbo.Usuario
(
  IdUsuario       INTEGER IDENTITY NOT NULL,
  NomUsuario      VARCHAR(1000)    NOT NULL,
  CpfUsuario      VARCHAR(11)      NOT NULL,
  SiapeUsuario    VARCHAR(10),
  DescLogin       VARCHAR(500)     NOT NULL,
  EmlUsuario      VARCHAR(200),
  TelUsuario      VARCHAR(25),
  ChaveApi        VARCHAR(32),
  DthUltimoLogin  DATETIME,
  FlgAtivo        BIT              NOT NULL,
  FreqRecebEmail  TINYINT,

  CONSTRAINT PK_Usuario PRIMARY KEY (IdUsuario)
);

CREATE TABLE dbo.UsuarioLog
(
  REV             INT           NOT NULL,
  REVTYPE         TINYINT,
  IdUsuario       INTEGER       NOT NULL,
  NomUsuario      VARCHAR(1000) NOT NULL,
  CpfUsuario      VARCHAR(11)   NOT NULL,
  SiapeUsuario    VARCHAR(10),
  DescLogin       VARCHAR(500)  NOT NULL,
  EmlUsuario      VARCHAR(200),
  TelUsuario      VARCHAR(25),
  ChaveApi        VARCHAR(32),
  FlgAtivo        BIT           NOT NULL,
  FreqRecebEmail  TINYINT
);

CREATE TABLE dbo.Perfil
(
  IdPerfil                    INTEGER IDENTITY NOT NULL,
  DescPerfil                  VARCHAR(500)     NOT NULL,
  FlgOferecidoPublicoExterno  BIT              NOT NULL,

  CONSTRAINT PK_Perfil PRIMARY KEY (IdPerfil)
);

CREATE TABLE dbo.PerfisDoUsuario
(
  IdPerfil  INT NOT NULL,
  IdUsuario INT NOT NULL,

  CONSTRAINT PK_PerfisDoUsuario PRIMARY KEY (IdPerfil, IdUsuario),
  CONSTRAINT FK_PerfisDoUsuario_Perfil FOREIGN KEY (IdPerfil) REFERENCES dbo.Perfil (IdPerfil),
  CONSTRAINT FK_PerfisDoUsuario_Usuario FOREIGN KEY (IdUsuario) REFERENCES dbo.Usuario (IdUsuario)
);

CREATE TABLE dbo.PermissoesDoPerfil
(
  IdPerfil  INT NOT NULL,
  IdPermissao varchar(1000) NOT NULL,

  CONSTRAINT PK_PermissoesDoPerfil PRIMARY KEY (IdPerfil, IdPermissao),
  CONSTRAINT FK_PermissoesDoPerfil_Perfil FOREIGN KEY (IdPerfil) REFERENCES dbo.Perfil (IdPerfil)
);

CREATE TABLE dbo.PerfisDoUsuarioLog
(
  REV       INT     NOT NULL,
  REVTYPE   TINYINT,
  IdPerfil  INTEGER NOT NULL,
  IdUsuario INTEGER NOT NULL,
);

CREATE TABLE dbo.TipoOrgao
(
  IdTipoOrgao  INT IDENTITY  NOT NULL,
  NomTipoOrgao VARCHAR(1000) NOT NULL,

  CONSTRAINT PK_TipoOrgao PRIMARY KEY (IdTipoOrgao)
);

CREATE TABLE dbo.Orgao
(
  IdOrgao     INT IDENTITY  NOT NULL,
  IdTipoOrgao INT           NOT NULL,
  NomOrgao    VARCHAR(1000) NOT NULL,
  SigOrgao    VARCHAR(20)   NOT NULL,
  CodOrgao    VARCHAR(20)   NOT NULL,
  FlgAtivo    BIT           NOT NULL,

  CONSTRAINT PK_Orgao PRIMARY KEY (IdOrgao),
  CONSTRAINT FK_Orgao_TipoOrgao FOREIGN KEY (IdTipoOrgao) REFERENCES dbo.TipoOrgao (IdTipoOrgao)
);

CREATE TABLE dbo.UF
(
  SigUF  CHAR(2)      NOT NULL,
  DescUF VARCHAR(50)  NOT NULL,

  CONSTRAINT PK_UF PRIMARY KEY (SigUF)
);

CREATE TABLE dbo.Municipio
(
  IdMunicipio   INT          NOT NULL,
  DescMunicipio VARCHAR(100) NOT NULL,
  SigUF         CHAR(2)      NOT NULL,

  CONSTRAINT PK_Municipio PRIMARY KEY (IdMunicipio),
  CONSTRAINT FK_Municipio_UF FOREIGN KEY (SigUF) REFERENCES dbo.UF (SigUF)
);

CREATE TABLE dbo.TipoUnidade
(
  IdTipoUnidade  INT IDENTITY  NOT NULL,
  NomTipoUnidade VARCHAR(1000) NOT NULL,

  CONSTRAINT PK_TipoUnidade PRIMARY KEY (IdTipoUnidade)
);

CREATE TABLE dbo.Unidade
(
  IdUnidade         INT IDENTITY  NOT NULL,
  IdOrgao           INT           NOT NULL,
  IdTipoUnidade     INT           NOT NULL,
  NomUnidade        VARCHAR(1000) NOT NULL,
  SigUnidade        VARCHAR(20)   NOT NULL,
  CodUnidade        VARCHAR(20)   NOT NULL,
  FlgAtivo          BIT           NOT NULL,
  IdUnidadeSuperior INTEGER,
  TelUnidade        VARCHAR(25),
  EmlUnidade        VARCHAR(200),
  IdMunicipio       INT           NOT NULL,
  DescHierarquia    VARCHAR(1000),

  CONSTRAINT PK_Unidade PRIMARY KEY (IdUnidade),
  CONSTRAINT FK_Unidade_Orgao FOREIGN KEY (IdOrgao) REFERENCES dbo.Orgao (IdOrgao),
  CONSTRAINT FK_Unidade_TipoUnidade FOREIGN KEY (IdTipoUnidade) REFERENCES dbo.TipoUnidade (IdTipoUnidade),
  CONSTRAINT FK_Unidade_Unidade FOREIGN KEY (idUnidadeSuperior) REFERENCES dbo.Unidade (IdUnidade)
);

CREATE TABLE dbo.UnidadeLog
(
  REV               INT           NOT NULL,
  REVTYPE           TINYINT,
  IdUnidade         INT           NOT NULL,
  IdOrgao           INT           NOT NULL,
  IdTipoUnidade     INT           NOT NULL,
  NomUnidade        VARCHAR(1000) NOT NULL,
  SigUnidade        VARCHAR(20)   NOT NULL,
  CodUnidade        VARCHAR(20)   NOT NULL,
  FlgAtivo          BIT           NOT NULL,
  idUnidadeSuperior INTEGER,
  TelUnidade        VARCHAR(25),
  EmlUnidade        VARCHAR(200),
  IdMunicipio       INT,
  DescHierarquia    VARCHAR(1000),
);


CREATE TABLE dbo.LotacoesDoUsuario
(
  IdUsuario INTEGER NOT NULL,
  IdUnidade INTEGER NOT NULL,

  CONSTRAINT PK_LotacoesDoUsuario PRIMARY KEY (IdUsuario, IdUnidade),
  CONSTRAINT FK_LotacoesDoUsuario_Usuario FOREIGN KEY (IdUsuario) REFERENCES dbo.Usuario (IdUsuario),
  CONSTRAINT FK_LotacoesDoUsuario_IdUnidade FOREIGN KEY (IdUnidade) REFERENCES dbo.Unidade (IdUnidade)
);

CREATE TABLE dbo.LotacoesDoUsuarioLog
(
  REV       INT     NOT NULL,
  REVTYPE   TINYINT,
  IdUnidade INTEGER NOT NULL,
  IdUsuario INTEGER NOT NULL
);


CREATE TABLE dbo.Configuracao
(
  id    VARCHAR(200)  NOT NULL,
  valor VARCHAR(2500) NOT NULL,

  CONSTRAINT PK_Configuracao PRIMARY KEY (id)
);

CREATE TABLE dbo.Revisao
(
  id         INT IDENTITY NOT NULL,
  dthRevisao DATETIME     NOT NULL,
  idUsuario  INT,

  CONSTRAINT PK_Revisao PRIMARY KEY (id)
);

CREATE TABLE dbo.Shedlock(
    name        VARCHAR(64),
    lock_until  TIMESTAMP(3) NULL,
    locked_at   TIMESTAMP(3) NULL,
    locked_by   VARCHAR(255),

    CONSTRAINT PK_shedlock PRIMARY KEY (name)
);


CREATE TABLE dbo.GestoresDaUnidade
(
  IdUnidade INTEGER NOT NULL,
  IdUsuario INTEGER NOT NULL,

  CONSTRAINT PK_GestoresDaUnidade PRIMARY KEY (IdUnidade, IdUsuario),
  CONSTRAINT FK_GestoresDaUnidade_IdUnidade FOREIGN KEY (IdUnidade) REFERENCES dbo.Unidade (IdUnidade),
  CONSTRAINT FK_GestoresDaUnidade_Usuario FOREIGN KEY (IdUsuario) REFERENCES dbo.Usuario (IdUsuario),
);

CREATE TABLE dbo.GestoresDaUnidadeLog
(
  REV       INT     NOT NULL,
  REVTYPE   TINYINT,
  IdUnidade INTEGER NOT NULL,
  IdUsuario INTEGER NOT NULL
);
