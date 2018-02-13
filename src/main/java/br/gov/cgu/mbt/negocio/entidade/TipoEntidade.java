package br.gov.cgu.mbt.negocio.entidade;

public enum TipoEntidade {
	
	LOCALIDADE("Localidade"), 
	ORGAO("Orgão/Entidade");
	
	private final String descricao;

	TipoEntidade(String descricao) {
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
