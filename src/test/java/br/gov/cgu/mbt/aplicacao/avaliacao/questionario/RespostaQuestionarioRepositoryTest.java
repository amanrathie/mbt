package br.gov.cgu.mbt.aplicacao.avaliacao.questionario;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;

import br.gov.cgu.test.entitymanager.InjetarEntityManager;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRule;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRuleBuilder;

public class RespostaQuestionarioRepositoryTest {
	
	@Rule
    public InjetarEntityManagerRule emRule = InjetarEntityManagerRuleBuilder.profilePadrao().build();

    @InjetarEntityManager
    private RespostaQuestionarioRepository repository = new RespostaQuestionarioRepository();
    
    @Test
    public void getPorIdAvaliacao_retorna_dados_corretos() {
    	Integer idAvaliacao = 0;
    	
    	assertThat(repository.getPorIdAvaliacao(idAvaliacao))
    		.isNotEmpty()
    		.extracting("avaliacao.id")
    		.contains(idAvaliacao);
    }

}
