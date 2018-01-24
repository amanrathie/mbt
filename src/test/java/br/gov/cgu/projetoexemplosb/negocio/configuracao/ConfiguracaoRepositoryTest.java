package br.gov.cgu.projetoexemplosb.negocio.configuracao;

import br.gov.cgu.test.entitymanager.InjetarEntityManager;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRule;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRuleBuilder;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class ConfiguracaoRepositoryTest {

    @Rule
    public InjetarEntityManagerRule emRule = InjetarEntityManagerRuleBuilder.profilePadrao().build();

    @InjetarEntityManager
    private ConfiguracaoRepository repository = new ConfiguracaoRepository();

    @Test
    public void getAll() throws Exception {
        List<Configuracao> all = repository.getAll();
        assertThat(all, hasSize(1));
    }

}