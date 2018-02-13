package br.gov.cgu.mbt.entidadeavaliadora;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import br.gov.cgu.mbt.aplicacao.entidadeavaliadora.BuscadorDeEntidadeAvaliadora;
import br.gov.cgu.mbt.aplicacao.entidadeavaliadora.EntidadeAvaliadoraRepository;
import br.gov.cgu.mbt.negocio.entidadeavaliadora.EntidadeAvaliadora;

public class BuscadorDeEntidadeAvaliadoraTest {
	
	private EntidadeAvaliadoraRepository repository = mock(EntidadeAvaliadoraRepository.class);
	private BuscadorDeEntidadeAvaliadora buscador = new BuscadorDeEntidadeAvaliadora(repository);
	
	@Test
	public void buscarPorNome_deve_retornar_dados_repository() {
		String nome = "CGU";
		List<EntidadeAvaliadora> respostaConsulta = Arrays.asList(EntidadeAvaliadora.builder().nome(nome).build());
		when(repository.getPorTermo(nome)).thenReturn(respostaConsulta);
		
		assertThat(buscador.buscarPorNome(nome)).isEqualTo(respostaConsulta);
	}

}
