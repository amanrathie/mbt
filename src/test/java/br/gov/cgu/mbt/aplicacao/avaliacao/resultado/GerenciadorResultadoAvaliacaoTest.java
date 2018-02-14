package br.gov.cgu.mbt.aplicacao.avaliacao.resultado;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import br.gov.cgu.mbt.negocio.avaliacao.resultado.ResultadoAvaliacao;

public class GerenciadorResultadoAvaliacaoTest {
	
	private ResultadoAvaliacaoRepository repository = mock(ResultadoAvaliacaoRepository.class);
	private GerenciadorResultadoAvaliacao gerenciador = new GerenciadorResultadoAvaliacao(repository);
	
	@Test
	public void salvar_salva_corretamente() {
		ResultadoAvaliacao resultado = mockResultado();
		
		when(repository.put(resultado)).thenReturn(resultado);
		
		assertThat(gerenciador.salvar(resultado)).isEqualTo(resultado);
	}
	
	private ResultadoAvaliacao mockResultado() {
		return ResultadoAvaliacao.builder()
				.build();
	}
}
