package br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder;

import java.util.Arrays;
import java.util.List;

import br.gov.cgu.mbt.negocio.avaliacao.bloco.Bloco;

/**
 * Constrói o questionário da EBT a ser migrado (1,2,3)
 */
public class BlocoEbtBuilder {

	public List<Bloco> build() {
		Bloco bloco1 = Bloco.builder()
				.nome("Regulamentação")
				.build();
		
		Bloco bloco2 = Bloco.builder()
				.nome("Transparência Passiva")
				.build();
		
		return Arrays.asList(bloco1, bloco2);
	}
}
