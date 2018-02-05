package br.gov.cgu.mbt.negocio;

public enum TipoStatus {
	ATIVO("Ativo"),
	INATIVO("Inativo");
	
	private final String descricao;

	private TipoStatus(String descricao) {
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
