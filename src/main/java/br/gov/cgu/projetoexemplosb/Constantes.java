package br.gov.cgu.projetoexemplosb;

public final class Constantes {

    public static final String NOME_APLICACAO   = "@app.context@";

    public static final String SCHEMA_APLICACAO = "dbo";

    public static final Integer QTDE_MAXIMA_DIAS_INATIVIDADE_USUARIO = 90;

    public static final String MODEL_SUCESSO                    = "sucesso";
    public static final String OPERACAO_REALIZADA_COM_SUCESSO   = "Operação realizada com sucesso.";
    public static final String QUEBRA_DE_LINHA_DE_MENSAGEM      = "<br/>";

    public static final String TEMPLATE_EMAIL_NOTIFICACAO_INTERACAO_INDIVIDUAL  = "NOTIFICACAO_INTERACAO_INDIVIDUAL";
    public static final String TEMPLATE_EMAIL_NOTIFICACAO_INTERACAO_DIARIA      = "NOTIFICACAO_INTERACAO_DIARIA";
    public static final String TEMPLATE_EMAIL_NOTIFICACAO_INTERACAO_SEMANAL     = "NOTIFICACAO_INTERACAO_SEMANAL";

    public static final String SWAGGER_TAG_USUARIOS     = "Usuários";
    public static final String SWAGGER_TAG_INSTANCIAS   = "Instâncias";
    public static final String SWAGGER_TAG_UNIDADES     = "Unidades";
    public static final String SWAGGER_TAG_TRILHAS      = "Trilhas";
    public static final String SWAGGER_TAG_CAMPOS       = "Campos";
    public static final String SWAGGER_TAG_SUBCLASSE    = "Subclasse";
    public static final String SWAGGER_TAG_RISCO        = "Risco";

    //Permissões
    public static final String HAS_ROLE_ADMINISTRADOR       = "hasAuthority('Administrador')";

    public static final String PODE_CONSULTAR_USUARIOS      = "hasAnyAuthority('Administrador', 'Consulta de Usuários', 'Gerenciar Usuários')";
    public static final String PODE_GERENCIAR_USUARIOS      = "hasAnyAuthority('Administrador', 'Gerenciar Usuários')";
    public static final String PODE_GERENCIAR_CAMPOS        = "hasAnyAuthority('Administrador', 'Gerenciar Campos')";
    public static final String PODE_GERENCIAR_SUBCLASSES    = "hasAnyAuthority('Administrador', 'Gerenciar Subclasses')";
    public static final String PODE_GERENCIAR_TRILHAS       = "hasAnyAuthority('Administrador', 'Gerenciar Trilhas')";
    public static final String PODE_CONSULTAR_UNIDADES      = "hasAnyAuthority('Administrador', 'Consulta de Unidades', 'Gerenciar Unidades')";
    public static final String PODE_GERENCIAR_UNIDADES      = "hasAnyAuthority('Administrador', 'Gerenciar Unidades')";
    public static final String PODE_CONSULTAR_RISCOS        = "hasAnyAuthority('Administrador', 'Consulta de Riscos', 'Gerenciar Riscos')";
    public static final String PODE_GERENCIAR_RISCOS        = "hasAnyAuthority('Administrador', 'Gerenciar Riscos')";

    private Constantes() {
        /*Hide do construtor de classe utilitaria*/
    }

}
