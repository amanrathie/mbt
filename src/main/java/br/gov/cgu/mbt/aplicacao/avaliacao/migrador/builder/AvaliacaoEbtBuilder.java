package br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder;

import java.util.Arrays;
import java.util.List;

import br.gov.cgu.mbt.Constantes;
import br.gov.cgu.mbt.negocio.TipoPoder;
import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.TipoAvaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.TipoFaseAvaliacao;
import br.gov.cgu.mbt.negocio.entidadeavaliadora.EntidadeAvaliadora;

/**
 * Constrói as avaliações EBT a serem migradas (1,2,3)
 */
public class AvaliacaoEbtBuilder {

	public List<Avaliacao> build() {
		
		EntidadeAvaliadora cgu = EntidadeAvaliadora.builder().id(Constantes.ID_ENTIDADE_AVALIADORA_CGU).build();
		
		Avaliacao v1 = Avaliacao.builder()
				.nome("Escala Brasil Transparente v1")
				.tipo(TipoAvaliacao.INDEPENDENTE)
				.edicao(1)
				.fase(TipoFaseAvaliacao.PUBLICADA)
				.poder(TipoPoder.EXECUTIVO)
				.entidadeAvaliadora(cgu)
				.build();
		Avaliacao v2 = Avaliacao.builder()
				.nome("Escala Brasil Transparente v2")
				.tipo(TipoAvaliacao.INDEPENDENTE)
				.edicao(2)
				.fase(TipoFaseAvaliacao.PUBLICADA)
				.poder(TipoPoder.EXECUTIVO)
				.entidadeAvaliadora(cgu)
				.build();
		Avaliacao v3 = Avaliacao.builder()
				.nome("Escala Brasil Transparente v3")
				.tipo(TipoAvaliacao.INDEPENDENTE)
				.edicao(3)
				.fase(TipoFaseAvaliacao.PUBLICADA)
				.poder(TipoPoder.EXECUTIVO)
				.entidadeAvaliadora(cgu)
				.build();
		
		return Arrays.asList(v1, v2, v3);
	}
}
