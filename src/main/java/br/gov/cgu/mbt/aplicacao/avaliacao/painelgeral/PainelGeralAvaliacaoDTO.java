package br.gov.cgu.mbt.aplicacao.avaliacao.painelgeral;

import com.querydsl.core.annotations.QueryProjection;

import br.gov.cgu.mbt.negocio.TipoPoder;
import br.gov.cgu.mbt.negocio.avaliacao.TipoAvaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.TipoFaseAvaliacao;
import br.gov.cgu.persistencia.querybuilder.Filtro;
import lombok.Data;

@Data
public class PainelGeralAvaliacaoDTO extends Filtro {

	private String nome;
	private String tipo;
	private String poder;
	private String fase;
	private boolean ativo;

	@QueryProjection
	public PainelGeralAvaliacaoDTO(String nome, int tipo, int poder, int fase, boolean ativo) {

		this.nome = nome;
		this.tipo = TipoAvaliacao.values()[tipo].getDescricao();
		this.poder = TipoPoder.values()[poder].getDescricao();
		this.fase = TipoFaseAvaliacao.values()[fase].getDescricao();
		this.ativo = ativo;
	}

}
