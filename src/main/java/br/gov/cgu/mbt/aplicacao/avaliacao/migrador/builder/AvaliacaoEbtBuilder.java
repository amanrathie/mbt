package br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder;

import java.util.Arrays;
import java.util.List;

import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.TipoFaseAvaliacao;

/**
 * Constrói as avaliações EBT a serem migradas (1,2,3)
 */
public class AvaliacaoEbtBuilder {

	public List<Avaliacao> build() {
		Avaliacao v1 = Avaliacao.builder()
				.nome("Escala Brasil Transparente v1")
				.fase(TipoFaseAvaliacao.PUBLICADA)
				.build();
		Avaliacao v2 = Avaliacao.builder()
				.nome("Escala Brasil Transparente v2")
				.fase(TipoFaseAvaliacao.PUBLICADA)
				.build();
		Avaliacao v3 = Avaliacao.builder()
				.nome("Escala Brasil Transparente v3")
				.fase(TipoFaseAvaliacao.PUBLICADA)
				.build();
		
		return Arrays.asList(v1, v2, v3);
	}
}
