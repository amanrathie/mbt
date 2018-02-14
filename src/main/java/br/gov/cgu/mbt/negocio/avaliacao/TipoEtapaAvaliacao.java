package br.gov.cgu.mbt.negocio.avaliacao;

public enum TipoEtapaAvaliacao {
	AVALIACAO("Avaliação"), 
	REVISAO("Revisão"), 
	VALIDACAO("Validação");
	
	private final String descricao;

	TipoEtapaAvaliacao(String descricao) {
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
