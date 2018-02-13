package br.gov.cgu.mbt.negocio;

public enum TipoStatus {
	INATIVO("Inativo"),
	ATIVO("Ativo");
	
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
