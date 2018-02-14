package br.gov.cgu.mbt.aplicacao.avaliacao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;

public class GerenciadorAvaliacaoTest {
	private AvaliacaoRepository repository = mock(AvaliacaoRepository.class);
	private GerenciadorAvaliacao gerenciador = new GerenciadorAvaliacao(repository);
	
	@Test
	public void salvar_salva_corretamente() {
		Avaliacao avaliacao = mockAvaliacao();
		
		when(repository.put(avaliacao)).thenReturn(avaliacao);
		
		assertThat(gerenciador.salvar(avaliacao)).isEqualTo(avaliacao);
	}
	
	private Avaliacao mockAvaliacao() {
		return Avaliacao.builder()
				.build();
	}
}
