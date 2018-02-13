package br.gov.cgu.mbt.aplicacao.avaliacao.resultado;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;

import br.gov.cgu.mbt.negocio.avaliacao.resultado.ResultadoAvaliacao;
import br.gov.cgu.test.entitymanager.InjetarEntityManager;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRule;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRuleBuilder;

public class ResultadoAvaliacaoRepositoryTest {
	
    @Rule
    public InjetarEntityManagerRule emRule = InjetarEntityManagerRuleBuilder.profilePadrao().build();

    @InjetarEntityManager
    private ResultadoAvaliacaoRepository repository = new ResultadoAvaliacaoRepository();
	
	@Test
	public void getPorIdAvaliacao_deve_filtrar_por_id_avaliacao() {
		Integer ID_AVALIACAO = 0;
		
		List<ResultadoAvaliacao> resultados = repository.getPorIdAvaliacao(ID_AVALIACAO);
		
		assertThat(resultados).isNotEmpty().extracting("avaliacao.id").contains(ID_AVALIACAO);
	}

}
