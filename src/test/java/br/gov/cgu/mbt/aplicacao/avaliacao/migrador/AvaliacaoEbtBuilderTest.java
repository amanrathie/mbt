package br.gov.cgu.mbt.aplicacao.avaliacao.migrador;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.AvaliacaoEbtBuilder;
import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;

public class AvaliacaoEbtBuilderTest {
	
	private AvaliacaoEbtBuilder avaliacaoEbtBuilder = new AvaliacaoEbtBuilder();
	
	@Test
	public void blocos_sao_construidos_corretamente() {
		assertThat(avaliacaoEbtBuilder.build())
			.isNotEmpty()
			.hasSize(3)
			.hasOnlyElementsOfType(Avaliacao.class);
	}
}
