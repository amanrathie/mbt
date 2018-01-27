package br.gov.cgu.mbt.aplicacao.unidade;

import br.gov.cgu.mbt.negocio.unidade.Unidade;
import br.gov.cgu.mbt.negocio.unidade.UnidadeRepository;
import br.gov.cgu.test.entitymanager.InjetarEntityManager;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRule;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRuleBuilder;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static com.google.common.primitives.Ints.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class UnidadeRepositoryTest {

    @Rule
    public InjetarEntityManagerRule emRule = InjetarEntityManagerRuleBuilder.profilePadrao().build();

    @InjetarEntityManager
    private UnidadeRepository repository = new UnidadeRepository();

    @Test
    public void getPorNomeOuCodigoOuSigla__deve_filtrar_por_nome() throws Exception {
        List<Unidade> unidades  = repository.getPorTermo("Ministerio");

        assertThat(unidades, hasSize(greaterThan(0)));
        for (Unidade unidade : unidades) {
            assertThat(unidade.getNome(), containsString("Ministerio"));
        }
    }

    @Test
    public void getPorNomeOuCodigoOuSigla__deve_filtrar_por_sigla() throws Exception {
        List<Unidade> unidades  = repository.getPorTermo("DTI");

        assertThat(unidades, hasSize(greaterThan(0)));
        for (Unidade unidade : unidades) {
            assertThat(unidade.getSigla(), containsString("DTI"));
        }
    }

    @Test
    public void getPorNomeOuCodigoOuSigla__deve_filtrar_por_codigo() throws Exception {
        List<Unidade> unidades  = repository.getPorTermo("200003");

        assertThat(unidades, hasSize(greaterThan(0)));
        for (Unidade unidade : unidades) {
            assertThat(unidade.getCodigo(), is("200003"));
        }
    }

    @Test
    public void getPorIds__deve_buscar_por_ids() throws Exception {
        //given
        List<Integer> ids = asList(1, 2, 3);

        //when
        List<Unidade> unidades = repository.getPorIds(ids);

        //then
        assertThat(unidades, hasSize(3));
        for (Unidade unidade : unidades) {
            assertThat(unidade.getId(), isIn(ids));
        }
    }

    @Test
    public void isGestor__deve_retornar_true_se_usuario_gestor() throws Exception {
        // when
        boolean compartilhado = repository.isGestor(7, 11);

        // when
        assertThat(compartilhado, is(Boolean.TRUE));
    }

    @Test
    public void isGestor__deve_retornar_false_se_usuario_nao_gestor() throws Exception {
        // when
        boolean compartilhado = repository.isGestor(7, 10);

        // when
        assertThat(compartilhado, is(Boolean.FALSE));
    }

}