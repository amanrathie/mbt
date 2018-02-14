package br.gov.cgu.mbt.aplicacao.avaliacao.questionario;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import br.gov.cgu.mbt.negocio.avaliacao.questionario.Questionario;

public class GerenciadorQuestionarioTest {
	
	private QuestionarioRepository repository = mock(QuestionarioRepository.class);
	private GerenciadorQuestionario gerenciador = new GerenciadorQuestionario(repository);
	
	@Test
	public void salvar_salva_corretamente() {
		Questionario questionario = mockQuestionario();
		
		when(repository.put(questionario)).thenReturn(questionario);
		
		assertThat(gerenciador.salvar(questionario)).isEqualTo(questionario);
	}
	
	private Questionario mockQuestionario() {
		return Questionario.builder()
				.build();
	}

}
