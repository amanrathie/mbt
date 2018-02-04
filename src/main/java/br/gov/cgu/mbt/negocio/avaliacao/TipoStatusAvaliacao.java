package br.gov.cgu.mbt.negocio.avaliacao;

public enum TipoStatusAvaliacao {
	ATIVO("Ativo"),
	INATIVO("Inativo");
	
	private final String descricao;

	private TipoStatusAvaliacao(String descricao) {
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
