package br.gov.cgu.mbt.entidadeavaliadora;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;

import br.gov.cgu.mbt.aplicacao.entidadeavaliadora.EntidadeAvaliadoraRepository;
import br.gov.cgu.test.entitymanager.InjetarEntityManager;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRule;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRuleBuilder;

public class EntidadeAvaliadoraRepositoryTest {
	
	@Rule
    public InjetarEntityManagerRule emRule = InjetarEntityManagerRuleBuilder.profilePadrao().build();

    @InjetarEntityManager
    private EntidadeAvaliadoraRepository repository = new EntidadeAvaliadoraRepository();
    
    @Test
    public void testGetPorTermo_retorna_dados() {
    	String TERMO_CGU = "CGU";
    	assertThat(repository.getPorTermo(TERMO_CGU))
    		.isNotEmpty()
    		.hasSize(1)
    		.extracting("nome")
    		.contains(TERMO_CGU);
    }
    
    @Test
    public void get_retorna_dados_usuarios_relacionados() {
    	assertThat(repository.get(0).usuarios)
    		.isNotEmpty()
    		.hasSize(2);
    }

}
