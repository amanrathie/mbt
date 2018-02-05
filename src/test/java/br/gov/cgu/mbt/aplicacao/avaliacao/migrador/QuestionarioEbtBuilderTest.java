package br.gov.cgu.mbt.aplicacao.avaliacao.migrador;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.QuestionarioEbtBuilder;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Bloco;

public class QuestionarioEbtBuilderTest {
	
	private QuestionarioEbtBuilder questionarioEbtBuilder = new QuestionarioEbtBuilder();
	
	// TODO: centralizar ponto de conversão/desconversão de questionario
	@Test
	public void questionario_json_construido_corretamente() throws Exception {
		String txtEstruturaQuestionario = questionarioEbtBuilder.build();
		
		System.out.println(txtEstruturaQuestionario);
		
		assertThat(txtEstruturaQuestionario)
			.isNotEmpty();
	}
	
	@Test
	public void questionario_json_reconstruido_corretamente() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String txtEstruturaQuestionario = questionarioEbtBuilder.build();

		List blocos = mapper.readValue(txtEstruturaQuestionario, List.class); // TODO: Ver como transformar para list de blocos
		System.out.println(blocos);
		
		assertThat(blocos)
			.isNotNull()
			.isNotEmpty()
			.hasOnlyElementsOfType(Bloco.class);
	}
}
