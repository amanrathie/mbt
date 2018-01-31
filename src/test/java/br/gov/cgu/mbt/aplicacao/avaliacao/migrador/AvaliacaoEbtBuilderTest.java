package br.gov.cgu.mbt.aplicacao.avaliacao.migrador;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.AvaliacaoEbtBuilder;
import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;

public class AvaliacaoEbtBuilderTest {
	
	private AvaliacaoEbtBuilder avaliacaoEbtBuilder = new AvaliacaoEbtBuilder();
	
	//https://github.com/joel-costigliola/assertj-examples/blob/master/assertions-examples/src/test/java/org/assertj/examples/StreamAssertionsExamples.java
	@Test
	public void avaliacoes_sao_construidas_corretamente() {
		assertThat(avaliacaoEbtBuilder.build())
			.isNotEmpty()
			.hasSize(3)
			.hasOnlyElementsOfType(Avaliacao.class);
	}
}
