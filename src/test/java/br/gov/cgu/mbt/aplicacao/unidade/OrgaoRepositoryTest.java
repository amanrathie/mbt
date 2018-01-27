package br.gov.cgu.mbt.aplicacao.unidade;

import br.gov.cgu.mbt.negocio.unidade.Orgao;
import br.gov.cgu.mbt.negocio.unidade.OrgaoRepository;
import br.gov.cgu.test.entitymanager.InjetarEntityManager;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRule;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRuleBuilder;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static com.google.common.primitives.Ints.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class OrgaoRepositoryTest {

    @Rule
    public InjetarEntityManagerRule emRule = InjetarEntityManagerRuleBuilder.profilePadrao().build();

    @InjetarEntityManager
    private OrgaoRepository repository = new OrgaoRepository();

    @Test
    public void getPorNomeOuCodigoOuSigla__deve_filtrar_por_nome() throws Exception {
        List<Orgao> orgaos  = repository.getPorTermo("Presid");

        assertThat(orgaos, hasSize(greaterThan(0)));
        for (Orgao orgao : orgaos) {
            assertThat(orgao.getNome(), containsString("Presid"));
        }
    }

    @Test
    public void getPorNomeOuCodigoOuSigla__deve_filtrar_por_sigla() throws Exception {
        List<Orgao> orgaos  = repository.getPorTermo("STF");

        assertThat(orgaos, hasSize(greaterThan(0)));
        for (Orgao orgao : orgaos) {
            assertThat(orgao.getSigla(), containsString("STF"));
        }
    }

    @Test
    public void getPorNomeOuCodigoOuSigla__deve_filtrar_por_codigo() throws Exception {
        List<Orgao> orgaos  = repository.getPorTermo("100001");

        assertThat(orgaos, hasSize(greaterThan(0)));
        for (Orgao orgao : orgaos) {
            assertThat(orgao.getCodigo(), is("100001"));
        }
    }

    @Test
    public void getPorIds__deve_buscar_por_ids() throws Exception {
        //given
        List<Integer> ids = asList(1, 2);

        //when
        List<Orgao> orgaos = repository.getPorIds(ids);

        //then
        assertThat(orgaos, hasSize(2));
        for (Orgao orgao : orgaos) {
            assertThat(orgao.getId(), isIn(ids));
        }
    }
}