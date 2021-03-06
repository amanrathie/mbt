package br.gov.cgu.mbt.negocio.avaliacao.questao;

public enum TipoQuestao {
	DESCRITIVA("Descritiva"), 
	MULTIPLA_ESCOLHA("Múltipla Escolha"), 
	ESCALA("Escala"), 
	MATRIZ("Matriz");
	
	private final String descricao;

	TipoQuestao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
