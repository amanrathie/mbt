package br.gov.cgu.mbt.aplicacao.painelgeral;

import org.junit.Rule;
import org.junit.Test;

import br.gov.cgu.mbt.aplicacao.avaliacao.painelgeral.PainelGeralAvaliacaoDTO;
import br.gov.cgu.mbt.aplicacao.avaliacao.painelgeral.PainelGeralAvaliacaoFiltro;
import br.gov.cgu.mbt.aplicacao.avaliacao.painelgeral.PainelGeralAvaliacaoQueryBuilder;
import br.gov.cgu.mbt.infraestrutura.QueryBuilderTest;
import br.gov.cgu.persistencia.querybuilder.QueryBuilderJPASQL;
import br.gov.cgu.test.entitymanager.InjetarEntityManager;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRule;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRuleBuilder;

public class PainelGeralAvaliacaoQueryBuilderTest extends QueryBuilderTest<PainelGeralAvaliacaoFiltro, PainelGeralAvaliacaoDTO> {
 
    private static final int MAXIMO_REGISTROS_NO_DADOS = 3;

    @Rule
    public InjetarEntityManagerRule emRule = InjetarEntityManagerRuleBuilder.profilePadrao().build();

    @InjetarEntityManager
    private PainelGeralAvaliacaoQueryBuilder queryBuilder = new PainelGeralAvaliacaoQueryBuilder();

    @Override
    public QueryBuilderJPASQL<PainelGeralAvaliacaoFiltro, PainelGeralAvaliacaoDTO> getQueryBuilder() {
        return queryBuilder;
    }

    @Test
    public void testarCombinacoes() throws Exception {
        testarQuery(criarFiltro(), MAXIMO_REGISTROS_NO_DADOS);
    }

    private PainelGeralAvaliacaoFiltro criarFiltro() {
    	PainelGeralAvaliacaoFiltro filtro = new PainelGeralAvaliacaoFiltro();
    	
        
    	return filtro;
    }

}