package br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder;

import java.util.Arrays;
import java.util.List;

import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;

/**
 * Constrói as avaliações EBT a serem migradas (1,2,3)
 */
public class AvaliacaoEbtBuilder {

	public List<Avaliacao> build() {
		Avaliacao v1 = Avaliacao.builder().nome("Escala Brasil Transparente v1").build();
		Avaliacao v2 = Avaliacao.builder().nome("Escala Brasil Transparente v2").build();
		Avaliacao v3 = Avaliacao.builder().nome("Escala Brasil Transparente v3").build();
		
		return Arrays.asList(v1, v2, v3);
	}
}
