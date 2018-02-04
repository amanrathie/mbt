package br.gov.cgu.mbt.negocio.avaliacao;

public enum TipoPoderAvaliacao {
	LEGISLATIVO("Legislativo"), 
	JUDICIARIO("Judiciário"), 
	EXECUTIVO("Executivo");

	private final String descricao;

	private TipoPoderAvaliacao(String descricao) {
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
