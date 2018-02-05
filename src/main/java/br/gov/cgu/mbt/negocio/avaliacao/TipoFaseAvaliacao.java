package br.gov.cgu.mbt.negocio.avaliacao;

public enum TipoFaseAvaliacao {
	QUESTIONARIO_EM_APROVACAO("Questionário em aprovação"), 
	QUESTIONARIO_EM_APROVACAO_SA("Questionário aprovado (sem andamento)"),
	EM_PLANEJAMENTO("Em planejamento"),
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
