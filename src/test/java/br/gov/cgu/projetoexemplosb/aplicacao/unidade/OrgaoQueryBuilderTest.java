package br.gov.cgu.projetoexemplosb.aplicacao.unidade;

import br.gov.cgu.projetoexemplosb.negocio.unidade.TipoOrgao;
import br.gov.cgu.persistencia.querybuilder.QueryBuilderJPASQL;
import br.gov.cgu.test.entitymanager.InjetarEntityManager;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRule;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRuleBuilder;
import infraestrutura.QueryBuilderTest;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static infraestrutura.AssertUtils.assertListaOrdenadaPorPropriedade;

public class OrgaoQueryBuilderTest extends QueryBuilderTest<OrgaoFiltro, OrgaoDTO> {

    private static final int MAXIMO_REGISTROS_NO_DADOS = 7;

    @Rule
    public InjetarEntityManagerRule emRule = InjetarEntityManagerRuleBuilder.profilePadrao().build();

    @InjetarEntityManager
    private OrgaoQueryBuilder queryBuilder = new OrgaoQueryBuilder();

    @Override
    public QueryBuilderJPASQL<OrgaoFiltro, OrgaoDTO> getQueryBuilder() {
        return queryBuilder;
    }

    @Test
    public void testarCombinacoes() throws Exception {
        testarQuery("id", criarFiltro(null, null, null, null, null), MAXIMO_REGISTROS_NO_DADOS);
        testarQuery("tipo", criarFiltro(null, null, null, null, null), MAXIMO_REGISTROS_NO_DADOS);
        testarQuery("nome", criarFiltro(null, null, null, null, null), MAXIMO_REGISTROS_NO_DADOS);
        testarQuery("sigla", criarFiltro(null, null, null, null, null), MAXIMO_REGISTROS_NO_DADOS);
        testarQuery("codigo", criarFiltro(null, null, null, null, null), MAXIMO_REGISTROS_NO_DADOS);

        testarQuery("id", criarFiltro("Presid", null, null, null, null), 1);
        testarQuery("id", criarFiltro(null, TipoOrgao.ADMINISTRACAO_DIRETA_ESTADUAL, null, null, null), 2);
        testarQuery("id", criarFiltro(null, null, "100001", null, null), 1);
        testarQuery("id", criarFiltro(null, null, null, "CGU", null), 1);
        testarQuery("id", criarFiltro(null, null, null, null, false), 1);
    }

    @Override
    protected void assertOrdenacao(List<OrgaoDTO> resultado, String colunaOrdenacao) {
        assertListaOrdenadaPorPropriedade(resultado, (o1, o2) -> {
            switch (colunaOrdenacao) {
                case "tipo" : return o2.getTipo().compareTo(o1.getTipo());
                case "nome" : return o2.getNome().compareTo(o1.getNome());
                case "sigla" : return o2.getSigla().compareTo(o1.getSigla());
                case "codigo" : return o2.getCodigo().compareTo(o1.getCodigo());
                default : return o2.getId().compareTo(o1.getId());
            }
        });
    }

    private OrgaoFiltro criarFiltro(String nome, TipoOrgao tipo, String codigo, String sigla, Boolean ativo) {
        OrgaoFiltro u = new OrgaoFiltro();
        u.setNome(nome);
        u.setTipo(tipo);
        u.setCodigo(codigo);
        u.setSigla(sigla);
        u.setAtivo(ativo);
        return u;
    }

}