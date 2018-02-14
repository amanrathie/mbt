package br.gov.cgu.mbt.aplicacao.entidadeavaliada;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;

import br.gov.cgu.mbt.aplicacao.entidadeavaliada.LocalidadeRepository;
import br.gov.cgu.mbt.negocio.TipoPoder;
import br.gov.cgu.test.entitymanager.InjetarEntityManager;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRule;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRuleBuilder;

public class LocalidadeRepositoryTest {
	
	@Rule
    public InjetarEntityManagerRule emRule = InjetarEntityManagerRuleBuilder.profilePadrao().build();

    @InjetarEntityManager
    private LocalidadeRepository repository = new LocalidadeRepository();
    
    @Test
    public void getAll_retorna_dados() {
    	assertThat(repository.getAll())
    		.isNotEmpty()
    		.extracting("poder")
    		.contains(TipoPoder.EXECUTIVO);
    }

}
