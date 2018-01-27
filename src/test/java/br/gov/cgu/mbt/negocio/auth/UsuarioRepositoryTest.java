package br.gov.cgu.mbt.negocio.auth;

import br.gov.cgu.mbt.negocio.auth.Usuario;
import br.gov.cgu.mbt.negocio.auth.UsuarioRepository;
import br.gov.cgu.mbt.negocio.unidade.Unidade;
import br.gov.cgu.persistencia.jpa.EntidadeNaoEncontradaException;
import br.gov.cgu.test.entitymanager.InjetarEntityManager;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRule;
import br.gov.cgu.test.entitymanager.InjetarEntityManagerRuleBuilder;
import org.junit.Rule;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UsuarioRepositoryTest {

    @Rule
    public InjetarEntityManagerRule emRule = InjetarEntityManagerRuleBuilder.profilePadrao().build();

    @InjetarEntityManager
    private static UsuarioRepository repository = new UsuarioRepository();

    @Test
    public void getPorLogin() {
        Usuario admin = repository.getPorLogin("CGU\\renanlf");

        assertThat(admin.getLogin(), is("CGU\\renanlf"));
    }

    @Test
    public void getPorChaveApi__deve_recuperar_um_usuario_especifico_com_a_chaveApi_informada() {
        Usuario user = repository.getPorChaveApi("0fadfb8fd4f7cc48f04eafa048c32635");

        assertThat(user.getLogin(), is("CGU\\USER_CGSIS_TESTE_01"));
    }

    @Test
    public void getPorCpf__deve_recuperar_um_usuario_especifico_com_o_cpf_informado() {
        Usuario user = repository.getPorCpf("10000000001");

        assertThat(user.getLogin(), is("CGU\\USER_CGSIS_TESTE_01"));

    }

    @Test(expected = EntidadeNaoEncontradaException.class)
    public void getPorCpf__deve_lancar_exception_se_nao_encontrar() {
        repository.getPorCpf("602658761681");
    }

    @Test
    public void getPorTermo__deve_retornar_usuarios_que_tenham_o_termo_no_nome() {
        String termo = "Teste";

        List<Usuario> usuarios = repository.getPorTermo(termo);

        assertThat(usuarios, hasSize(10));

        for (Usuario usuario : usuarios) {
            assertThat(usuario.getNome(), containsString(termo));
        }
    }

    @Test
    public void getPorTermo__deve_retornar_usuarios_que_tenham_o_termo_no_cpf() {
        String termo = "10000000001";

        List<Usuario> usuarios = repository.getPorTermo(termo);

        assertThat(usuarios, hasSize(1));

        for (Usuario usuario : usuarios) {
            assertThat(usuario.getCpf(), is(termo));
        }
    }

    @Test
    public void getPorIds__deve_recuperar_usuarios_com_os_ids_informados() {
        List<Integer> ids = asList(1, 27, 28);

        List<Usuario> usuarios = repository.getPorIds(ids);

        assertThat(usuarios, hasSize(3));

        for (Usuario usuario : usuarios) {
            assertThat(usuario.getId(), isIn(ids));
        }
    }

    @Test
    public void getPorUnidades__deve_retornar_lista_usuarios_lotados_nas_unidades_informadas() {
        Unidade unidade = new Unidade();
        unidade.setId(7);
        List<Unidade> unidades = Collections.singletonList(unidade);

        List<Usuario> usuarios = repository.getPorUnidades(unidades);

        assertThat(usuarios, hasSize(greaterThan(0)));
    }

    @Test
    public void buscarHierarquiaUnidadesUsuario__deve_retornar_lista_de_unidades_da_hierarquia_das_lotacoes_do_usuario() throws Exception {
        Usuario usuario = repository.getPorLogin("CGU\\USER_CGSIS_TESTE_01");
        List<Unidade> unidades = repository.buscarHierarquiaUnidadesUsuario(usuario);

        assertThat(unidades, hasSize(greaterThan(2)));
    }

    @Test
    public void getPorDataDeNotificacao__deve_retornar_usuarios_com_notificacoes_na_data_informada() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse("02/10/2017", formato);

//        List<UsuarioProjection> usuarios = repository.getUsuariosComNotificacaoNaData(data);

//        assertThat(usuarios, hasSize(2));
    }

    @Test
    public void getPorPeriodoDeNotificacao__deve_retornar_usuarios_com_notificacoes_no_periodo_informada() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInicial = LocalDate.parse("02/10/2017", formato);
        LocalDate dataFinal   = LocalDate.parse("26/10/2017", formato);

//        List<UsuarioProjection> usuarios = repository.getUsuariosComNotificacaoNoPeriodo(dataInicial, dataFinal);

//        assertThat(usuarios, hasSize(3));
    }
}