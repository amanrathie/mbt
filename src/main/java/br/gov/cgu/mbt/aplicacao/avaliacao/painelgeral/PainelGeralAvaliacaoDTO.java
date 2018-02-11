package br.gov.cgu.mbt.aplicacao.avaliacao.painelgeral;

import com.querydsl.core.annotations.QueryProjection;

import br.gov.cgu.mbt.negocio.avaliacao.TipoAvaliacao;
import br.gov.cgu.persistencia.querybuilder.Filtro;
import lombok.Data;

@Data
public class PainelGeralAvaliacaoDTO extends Filtro {
	
	private String nome;	
	private String tipo;

	@QueryProjection
	public PainelGeralAvaliacaoDTO(String nome, int tipo) {
		
		this.nome = nome;
		this.tipo = TipoAvaliacao.values()[tipo].name();
	}
	
	
}
