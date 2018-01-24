package br.gov.cgu.projetoexemplosb.aplicacao.auth;

import br.gov.cgu.persistencia.querybuilder.QueryBuilderJPASQL;
import br.gov.cgu.test.entitymanager.InjetarEntityManager;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRule;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRuleBuilder;
import infraestrutura.QueryBuilderTest;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static infraestrutura.AssertUtils.assertListaOrdenadaPorPropriedade;

public class UsuarioQueryBuilderTest extends QueryBuilderTest<UsuarioFiltro, UsuarioDTO> {
    private static final int MAXIMO_REGISTROS_NO_DADOS = 15;
    
    @Rule
    public InjetarEntityManagerRule emRule = InjetarEntityManagerRuleBuilder.profilePadrao().build();

    @InjetarEntityManager
    private UsuarioQueryBuilder queryBuilder = new UsuarioQueryBuilder();

    @Test
    public void testarCombinacoes() {
    	testarQuery("id", criarFiltro("Teste ADMIN 1", null, null, null, null, null, null), 1);
        testarQuery("id", criarFiltro(null, "10000000001", null, null, null, null, null), 1);
        testarQuery("id", criarFiltro(null, null, "01455691", null, null, null, null), 1);
        testarQuery("id", criarFiltro(null, null, null, "CGU\\gabrielcp", null, null, null), 1);
        testarQuery("id", criarFiltro(null, null, null, null, Arrays.asList(1, 2), null, null), 2);
        testarQuery("id", criarFiltro(null, null, null, null, null, Collections.singletonList(2), null), 3);
        testarQuery("id", criarFiltro(null, null, null, null, null, null, true), 14);
        testarQuery("id", criarFiltro(null, null, null, null, null, null, false), 1);
        
        testarQuery("id", criarFiltro(null, null, null, null, null, null, null), MAXIMO_REGISTROS_NO_DADOS);
        testarQuery("cpf", criarFiltro(null, null, null, null, null, null, null), MAXIMO_REGISTROS_NO_DADOS);
        testarQuery("nome", criarFiltro(null, null, null, null, null, null, null), MAXIMO_REGISTROS_NO_DADOS);
        testarQuery("login", criarFiltro(null, null, null, null, null, null, null), MAXIMO_REGISTROS_NO_DADOS);
    }

    private UsuarioFiltro criarFiltro(String nome, String cpf, String siape, String login, List<Integer> unidades, List<Integer> perfis, Boolean ativos) {
        UsuarioFiltro u = new UsuarioFiltro();
        
        u.setNome(nome);
        u.setCpf(cpf);
        u.setSiape(siape);
        u.setLogin(login);
        u.setAtivo(ativos);
        u.setUnidades(unidades);
        u.setPerfis(perfis);
        
        return u;
    }

    @Override
    protected void assertOrdenacao(List<UsuarioDTO> resultado, String colunaOrdenacao) {
        assertListaOrdenadaPorPropriedade(resultado, (o1, o2) -> {
            switch (colunaOrdenacao) {
                case "cpf" : return o2.getCpf().compareTo(o1.getCpf());
                case "nome" : return o2.getNome().compareToIgnoreCase(o1.getNome());
                case "login" : return o2.getLogin().compareToIgnoreCase(o1.getLogin());
                default : return o2.getId().compareTo(o1.getId());
            }
        });
    }
    
    @Override
    public QueryBuilderJPASQL<UsuarioFiltro, UsuarioDTO> getQueryBuilder() {
        return queryBuilder;
    }
}