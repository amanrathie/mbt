package br.gov.cgu.mbt.aplicacao.avaliacao.questionario;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import br.gov.cgu.mbt.negocio.avaliacao.questionario.RespostaQuestionario;

public class BuscadorRespostaQuestionarioTest {

	private RespostaQuestionarioRepository repository = mock(RespostaQuestionarioRepository.class);
	private BuscadorRespostaQuestionario buscador = new BuscadorRespostaQuestionario(repository);
	
	@Test
	public void getPorIdAvaliacao_retorna_lista() {
		Integer idAvaliacao = 0;
		
		when(repository.getPorIdAvaliacao(idAvaliacao)).thenReturn(mockRespostaQuestionarioList());
		
		assertThat(buscador.getPorIdAvaliacao(idAvaliacao)).isNotEmpty().hasSize(2);
	}
	
	private List<RespostaQuestionario> mockRespostaQuestionarioList() {
		return Arrays.asList(RespostaQuestionario.builder()
						.build(),
						RespostaQuestionario.builder()
						.build());
	}
	

}
