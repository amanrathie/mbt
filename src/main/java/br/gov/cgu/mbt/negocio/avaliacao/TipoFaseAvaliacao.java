package br.gov.cgu.mbt.negocio.avaliacao;

public enum TipoFaseAvaliacao {
	QUESTIONARIO_EM_APROVACAO("Questionário em aprovação"), 
	EM_PLANEJAMENTO("Em planejamento"),
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
