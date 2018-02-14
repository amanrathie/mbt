package br.gov.cgu.mbt.negocio;

public enum TipoPoder {
	EXECUTIVO("Executivo"),
	LEGISLATIVO("Legislativo"), 
	JUDICIARIO("Judiciário"), 
	MINISTERIO_PUBLICO("Ministério Público");

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
