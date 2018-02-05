package br.gov.cgu.mbt.negocio.avaliacao;

public enum TipoFaseAvaliacao {
	EM_PLANEJAMENTO("Em planejamento"),
	QUESTIONARIO_EM_APROVACAO("Questionário em aprovação"), 
	QUESTIONARIO_EM_APROVACAO_SA("Questionário aprovado"),
	EM_ANDAMENTO("Em andamento"),
	AGUARDANDO_PUBLICACAO("Aguardando publicação"),
	PUBLICADA("Publicada");
	
	private final String descricao;

	TipoFaseAvaliacao(String descricao) {
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
