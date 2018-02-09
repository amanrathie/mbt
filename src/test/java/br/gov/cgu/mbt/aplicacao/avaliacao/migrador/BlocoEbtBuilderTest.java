package br.gov.cgu.mbt.aplicacao.avaliacao.migrador;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.BlocoEbtBuilder;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Bloco;

public class BlocoEbtBuilderTest {
	 
	private BlocoEbtBuilder questionarioEbtBuilder = new BlocoEbtBuilder();
	
	@Test
	public void blocos_construido_corretamente() throws Exception {
		List<Bloco> blocos = questionarioEbtBuilder.build();
		
		assertThat(blocos)
			.isNotEmpty()
			.hasSize(2);
	}
}
