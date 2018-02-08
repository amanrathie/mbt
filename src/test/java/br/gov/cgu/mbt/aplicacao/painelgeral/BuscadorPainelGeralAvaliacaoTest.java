package br.gov.cgu.mbt.aplicacao.painelgeral;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Test;

import br.gov.cgu.mbt.aplicacao.avaliacao.painelgeral.BuscadorPainelGeralAvaliacao;
import br.gov.cgu.mbt.aplicacao.avaliacao.painelgeral.PainelGeralAvaliacaoDTO;
import br.gov.cgu.mbt.aplicacao.avaliacao.painelgeral.PainelGeralAvaliacaoFiltro;
import br.gov.cgu.mbt.aplicacao.avaliacao.painelgeral.PainelGeralAvaliacaoQueryBuilder;
import br.gov.cgu.persistencia.querybuilder.RespostaConsulta;

public class BuscadorPainelGeralAvaliacaoTest {
	
	private PainelGeralAvaliacaoQueryBuilder queryBuilder = mock(PainelGeralAvaliacaoQueryBuilder.class);
	
	private BuscadorPainelGeralAvaliacao buscador = new BuscadorPainelGeralAvaliacao(queryBuilder);
	
	@Test
    public void buscar__deve_retornar_resultado_do_queryBuilder() throws Exception {
		PainelGeralAvaliacaoFiltro filtro = new PainelGeralAvaliacaoFiltro();
		RespostaConsulta<PainelGeralAvaliacaoDTO> respostaConsulta = new RespostaConsulta<>(Collections.emptyList(), 0L);
		
		given(queryBuilder.build(filtro)).willReturn(respostaConsulta);
		
		when(buscador.buscar(filtro));
		
		then(respostaConsulta);
	}

}
