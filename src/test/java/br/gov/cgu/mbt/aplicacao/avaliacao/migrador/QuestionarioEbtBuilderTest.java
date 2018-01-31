package br.gov.cgu.mbt.aplicacao.avaliacao.migrador;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.QuestionarioEbtBuilder;
import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.Questionario;

public class QuestionarioEbtBuilderTest {
	
	private QuestionarioEbtBuilder questionarioEbtBuilder = new QuestionarioEbtBuilder();
	
	@Test
	public void questionarios_sao_construidos_corretamente() {
		Avaliacao avaliacao = Avaliacao.builder().build();
		
		assertThat(questionarioEbtBuilder.build(avaliacao))
			.isNotNull()
			.isInstanceOf(Questionario.class);
	}
}
