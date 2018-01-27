package br.gov.cgu.mbt.aplicacao.unidade;

import br.gov.cgu.mbt.aplicacao.unidade.UnidadeDTO;
import br.gov.cgu.mbt.aplicacao.unidade.UnidadeFiltro;
import br.gov.cgu.mbt.aplicacao.unidade.UnidadeQueryBuilder;
import br.gov.cgu.mbt.negocio.unidade.TipoUnidade;
import br.gov.cgu.persistencia.querybuilder.QueryBuilderJPASQL;
import br.gov.cgu.test.entitymanager.InjetarEntityManager;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRule;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRuleBuilder;
import infraestrutura.QueryBuilderTest;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static infraestrutura.AssertUtils.assertListaOrdenadaPorPropriedade;

public class UnidadeQueryBuilderTest extends QueryBuilderTest<UnidadeFiltro, UnidadeDTO> {

    private static final int MAXIMO_REGISTROS_NO_DADOS = 7;

    @Rule
    public InjetarEntityManagerRule emRule = InjetarEntityManagerRuleBuilder.profilePadrao().build();

    @InjetarEntityManager
    private UnidadeQueryBuilder queryBuilder = new UnidadeQueryBuilder();

    @Override
    public QueryBuilderJPASQL<UnidadeFiltro, UnidadeDTO> getQueryBuilder() {
        return queryBuilder;
    }

    @Test
    public void testarCombinacoes() throws Exception {
        testarQuery("id", criarFiltro(null, null, null, null, null, null, null), MAXIMO_REGISTROS_NO_DADOS);
        testarQuery("tipo", criarFiltro(null, null, null, null, null, null, null), MAXIMO_REGISTROS_NO_DADOS);
        testarQuery("nome", criarFiltro(null, null, null, null, null, null, null), MAXIMO_REGISTROS_NO_DADOS);
        testarQuery("sigla", criarFiltro(null, null, null, null, null, null, null), MAXIMO_REGISTROS_NO_DADOS);
        testarQuery("codigo", criarFiltro(null, null, null, null, null, null, null), MAXIMO_REGISTROS_NO_DADOS);
        testarQuery("codigo", criarFiltro(null, null, null, null, null, null, null), MAXIMO_REGISTROS_NO_DADOS);

        testarQuery("id", criarFiltro("Diretoria", null, null, null, null, null, null), 2);
        testarQuery("id", criarFiltro(null, TipoUnidade.UAUD, null, null, null, null, null), 1);
        testarQuery("id", criarFiltro(null, null, "200002", null, null, null, null), 1);
        testarQuery("id", criarFiltro(null, null, null, "DTI", null, null, null), 4);
        testarQuery("id", criarFiltro(null, null, null, null, "GO", null, null), 2);
        testarQuery("id", criarFiltro(null, null, null, null, null, Arrays.asList(1,3,4), null), 3);
        testarQuery("id", criarFiltro(null, null, null, null, null, null, false), 1);
    }

    @Override
    protected void assertOrdenacao(List<UnidadeDTO> resultado, String colunaOrdenacao) {
        assertListaOrdenadaPorPropriedade(resultado, (o1, o2) -> {
            switch (colunaOrdenacao) {
                case "tipo" : return o2.getTipo().compareTo(o1.getTipo());
                case "nome" : return o2.getNome().compareToIgnoreCase(o1.getNome());
                case "sigla" : return o2.getSigla().compareToIgnoreCase(o1.getSigla());
                case "codigo" : return o2.getCodigo().compareToIgnoreCase(o1.getCodigo());
                case "uf" : return o2.getUf().compareToIgnoreCase(o1.getUf());
                case "municipio" : return o2.getMunicipio().compareToIgnoreCase(o1.getMunicipio());
                default : return o2.getId().compareTo(o1.getId());
            }
        });
    }

    private UnidadeFiltro criarFiltro(String nome, TipoUnidade tipo, String codigo, String sigla, String uf, List<Integer> idsMunicipio, Boolean ativo) {
        UnidadeFiltro u = new UnidadeFiltro();
        u.setNome(nome);
        u.setTipo(tipo);
        u.setCodigo(codigo);
        u.setSigla(sigla);
        u.setUf(uf);
        u.setIdsMunicipio(idsMunicipio);
        u.setAtivo(ativo);
        return u;
    }

}