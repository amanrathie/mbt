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
