package br.gov.cgu.mbt.aplicacao.avaliacao.migrador;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.QuestionarioEbtBuilder;
import br.gov.cgu.mbt.aplicacao.avaliacao.questionario.ConversorQuestionario;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Bloco;

public class QuestionarioEbtBuilderTest {
	 
	private QuestionarioEbtBuilder questionarioEbtBuilder = new QuestionarioEbtBuilder();
	
	@Test
	public void questionario_json_construido_corretamente() throws Exception {
		String jsonQuestionario = questionarioEbtBuilder.build();
		
		System.out.println(jsonQuestionario);
		
		assertThat(jsonQuestionario).isNotEmpty();
	}
	
	@Test
	public void questionario_json_reconstruido_corretamente() throws Exception {
		String jsonQuestionario = questionarioEbtBuilder.build();

		List<Bloco> blocos = ConversorQuestionario.toBlocos(jsonQuestionario);
		
		assertThat(blocos)
			.isNotNull()
			.isNotEmpty()
			.hasOnlyElementsOfType(Bloco.class);
	}
}
