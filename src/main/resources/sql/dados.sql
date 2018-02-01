-- Tipo Avaliação
INSERT INTO dbo.TipoAvaliacao VALUES (0, 'Avaliação Independente');
INSERT INTO dbo.TipoAvaliacao VALUES (1, 'Avaliação Cidadã');
INSERT INTO dbo.TipoAvaliacao VALUES (2, 'Auto Avaliação');

-- Tipo Questão
INSERT INTO dbo.TipoQuestao VALUES (0, 'Descritiva');
INSERT INTO dbo.TipoQuestao VALUES (1, 'Múltipla Escolha');
INSERT INTO dbo.TipoQuestao VALUES (2, 'Escala');
INSERT INTO dbo.TipoQuestao VALUES (3, 'Matriz');

-- Usuario/Perfil
INSERT INTO dbo.Perfil VALUES (0, 'Administrador CGU');
INSERT INTO dbo.Perfil VALUES (1, 'Administrador de Entidade');