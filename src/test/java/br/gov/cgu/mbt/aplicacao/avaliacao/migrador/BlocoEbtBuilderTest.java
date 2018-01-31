package br.gov.cgu.mbt.aplicacao.avaliacao.migrador;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.BlocoEbtBuilder;
import br.gov.cgu.mbt.negocio.avaliacao.bloco.Bloco;

public class BlocoEbtBuilderTest {
	
	private BlocoEbtBuilder blocoEbtBuilder = new BlocoEbtBuilder();
	
	@Test
	public void blocos_sao_construidos_corretamente() {
		assertThat(blocoEbtBuilder.build())
			.isNotEmpty()
			.hasSize(2)
			.hasOnlyElementsOfType(Bloco.class);
	}
}
