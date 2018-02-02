package br.gov.cgu.mbt.negocio.avaliacao;

public enum TipoAvaliacao {
	INDEPENDENTE("Avaliação Independente"), 
	CIDADA("Avaliação Cidadã"), 
	AUTOAVALIACAO("Auto Avaliação");
	
	private final String descricao;

	TipoAvaliacao(String descricao) {
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
