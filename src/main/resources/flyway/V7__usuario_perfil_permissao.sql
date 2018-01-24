CREATE TABLE dbo.PermissoesDoPerfil
(
  IdPerfil  INTEGER NOT NULL,
  IdPermissao varchar(1000) NOT NULL,

CONSTRAINT PK_PermissoesDoPerfil PRIMARY KEY (IdPerfil, IdPermissao),
CONSTRAINT FK_PermissoesDoPerfil_Perfil FOREIGN KEY (IdPerfil) REFERENCES dbo.Perfil (IdPerfil)
);


INSERT INTO dbo.PermissoesDoPerfil
(IdPerfil, IdPermissao)
VALUES
  (  0,  0),  (  1,  0),
  (  0,  1),  (  1,  1),
  (  0,  2),  (  1,  2),
  (  0,  3),  (  1,  3),
  (  0,  4),  (  1,  4),
  (  0,  5),  (  1,  5),
  (  0,  6),  (  1,  6),
  (  0,  7),  (  1,  7),
  (  0,  8),  (  1,  8),
  (  0,  9),  (  1,  9),
  (  0,  10), (  1,  10),
  (  0,  11), (  1,  11),
  (  0,  12), (  1,  12),
  (  0,  13), (  1,  13),
  (  0,  14), (  1,  14),
  (  0,  15), (  1,  15),
  (  2,  2),
  (  2,  3),
  (  2,  4),
  (  2,  6),
  (  2,  8),
  (  2,  12),
  (  3,  2);

DELETE from dbo.PerfisDoUsuario where idperfil > 3;
DELETE from dbo.PerfisQuePodemInstanciarDaSubclasse where idperfil>3;
DELETE from dbo.Perfil where idperfil > 3;

update dbo.Perfil set DescPerfil = 'Desenvolvedor DTI', FlgOferecidoPublicoExterno = 0 where idPerfil = 0;
update dbo.Perfil set DescPerfil = 'Gestor Sistema', FlgOferecidoPublicoExterno = 0 where idPerfil = 1;
update dbo.Perfil set DescPerfil = 'Auditor CGU', FlgOferecidoPublicoExterno = 0 where idPerfil = 2;
update dbo.Perfil set DescPerfil = 'Gestor de UPAG', FlgOferecidoPublicoExterno = 1 where idPerfil = 3;
