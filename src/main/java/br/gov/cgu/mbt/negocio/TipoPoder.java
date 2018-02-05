package br.gov.cgu.mbt.negocio;

public enum TipoPoder {
	LEGISLATIVO("Legislativo"), 
	JUDICIARIO("Judiciário"), 
	EXECUTIVO("Executivo");

	private final String descricao;

	private TipoPoder(String descricao) {
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
