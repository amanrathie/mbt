package br.gov.cgu.mbt.aplicacao.local;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;

import br.gov.cgu.mbt.negocio.local.MunicipioRepository;
import br.gov.cgu.test.entitymanager.InjetarEntityManager;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRule;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRuleBuilder;

public class MunicipioRepositoryTest {

    @Rule
    public InjetarEntityManagerRule emRule = InjetarEntityManagerRuleBuilder.profilePadrao().build();

    @InjetarEntityManager
    private MunicipioRepository repository = new MunicipioRepository();

    @Test
    public void getPorTermo_deve_filtrar_por_descricao() {
        String termo = "Bra";
        
        assertThat(repository.getPorTermo(termo))
        	.isNotEmpty();
    }
    
    @Test
    public void getPorCodigoIBGE_deve_filtrar_por_cod_ibge() {
        Integer codIBGE = 5300108;
        
        assertThat(repository.getPorCodigoIBGE(codIBGE))
        	.isNotNull();
    }
}