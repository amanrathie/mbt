package br.gov.cgu.mbt;

public final class Constantes {
    public static final String SCHEMA_APLICACAO = "dbo";

    public static final Integer QTDE_MAXIMA_REGISTROS_AUTOCOMPLETE = 20;
    
    public static final Integer ID_ENTIDADE_AVALIADORA_CGU = 0;
    
    public static final Integer ID_ADMIN_CGU_PADRAO = 0;
    public static final Integer ID_ADMIN_AVALIACAO_EBT_PADRAO = 1;
    
    public static final String ARQUIVO_EBT_MIGRACAO = "/ebt/ebt_respostas.csv";

    private Constantes() {
        /*Hide do construtor de classe utilitaria*/
    }

}
