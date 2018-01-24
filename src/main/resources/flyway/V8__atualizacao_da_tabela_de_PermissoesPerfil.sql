delete from dbo.PermissoesDoPerfil;

INSERT INTO dbo.PermissoesDoPerfil
(IdPerfil, IdPermissao)
VALUES
  (  0,  'GERENCIAR_TODAS_INSTANCIAS'),       (  1,  'GERENCIAR_TODAS_INSTANCIAS'),
  (  0,  'MENU_ADMINISTRACAO'),               (  1,  'MENU_ADMINISTRACAO'),
  (  0,  'MENU_AUDITORIA_CONTINUA'),          (  1,  'MENU_AUDITORIA_CONTINUA'),
  (  0,  'MENU_PLANEJAMENTO'),                (  1,  'MENU_PLANEJAMENTO'),
  (  0,  'CONSULTAR_USUARIOS'),               (  1,  'CONSULTAR_USUARIOS'),
  (  0,  'GERENCIAR_USUARIOS'),               (  1,  'GERENCIAR_USUARIOS'),
  (  0,  'CONSULTAR_UNIDADES'),               (  1,  'CONSULTAR_UNIDADES'),
  (  0,  'GERENCIAR_UNIDADES'),               (  1,  'GERENCIAR_UNIDADES'),
  (  0,  'CONSULTAR_TRILHAS'),                (  1,  'CONSULTAR_TRILHAS'),
  (  0,  'GERENCIAR_TRILHAS'),                (  1,  'GERENCIAR_TRILHAS'),
  (  0,  'GERENCIAR_CAMPOS'),                 (  1,  'GERENCIAR_CAMPOS'),
  (  0,  'GERENCIAR_SUBCLASSES'),             (  1,  'GERENCIAR_SUBCLASSES'),
  (  0,  'CONSULTAR_RISCOS'),                 (  1,  'CONSULTAR_RISCOS'),
  (  0,  'GERENCIAR_RISCOS'),                 (  1,  'GERENCIAR_RISCOS'),
  (  0,  'GERENCIAR_AREAS_DE_CONHECIMENTO'),  (  1,  'GERENCIAR_AREAS_DE_CONHECIMENTO'),
  (  0,  'GERENCIAR_AVISOS'),                 (  1,  'GERENCIAR_AVISOS'),
  (  2,  'MENU_AUDITORIA_CONTINUA'),
  (  2,  'MENU_PLANEJAMENTO'),
  (  2,  'CONSULTAR_USUARIOS'),
  (  2,  'CONSULTAR_UNIDADES'),
  (  2,  'CONSULTAR_TRILHAS'),
  (  2,  'CONSULTAR_RISCOS'),
  (  3,  'MENU_AUDITORIA_CONTINUA');