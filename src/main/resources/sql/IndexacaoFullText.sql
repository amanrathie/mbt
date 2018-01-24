CREATE FULLTEXT CATALOG [Unidade_catalog] WITH ACCENT_SENSITIVITY = OFF;
GO

CREATE FULLTEXT INDEX ON [dbo].[Unidade]
(
[NomUnidade] Language 1046,
[SigUnidade] Language 1046,
[CodUnidade] Language 1046
)
KEY INDEX PK_Unidade
ON [Unidade_catalog];
GO
----------------------------------------------------------------------------------------------------
CREATE FULLTEXT CATALOG [Usuario_catalog] WITH ACCENT_SENSITIVITY = OFF;
GO

CREATE FULLTEXT INDEX ON [dbo].[Usuario]
(
[NomUsuario] Language 1046,
[CpfUsuario] Language 1046,
[DescLogin] Language 1046
)
KEY INDEX PK_Usuario
ON [Usuario_catalog];
GO