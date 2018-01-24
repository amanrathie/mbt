package br.gov.cgu.projetoexemplosb.aplicacao.auth;

import br.gov.cgu.projetoexemplosb.aplicacao.unidade.BuscadorDeUnidade;
import br.gov.cgu.projetoexemplosb.negocio.auth.*;
import br.gov.cgu.projetoexemplosb.negocio.unidade.Unidade;
import br.gov.cgu.persistencia.jpa.EntidadeInvalidaException;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class GerenciadorDeUsuarioTest {

    private UsuarioRepository usuarioRepository = mock(UsuarioRepository.class);
    private PerfilRepository perfilRepository = mock(PerfilRepository.class);
    private BuscadorDeUsuario buscadorDeUsuario = mock(BuscadorDeUsuario.class);
    private BuscadorDeUnidade buscadorDeUnidade = mock(BuscadorDeUnidade.class);

    private GerenciadorDeUsuario gerenciador = new GerenciadorDeUsuario(usuarioRepository, perfilRepository,
            buscadorDeUsuario, buscadorDeUnidade);

    @Test(expected = EntidadeInvalidaException.class)
    public void salvar_deve_lancar_exception_se_entidade_for_invalida() throws Exception {
        Usuario usuario = mock(Usuario.class);
        when(buscadorDeUsuario.getUsuarioLogado()).thenReturn(usuario);
        when(usuarioRepository.put(any())).thenThrow(new EntidadeInvalidaException(""));

        gerenciador.salvar(new UsuarioForm());
    }

    @Test
    public void salvar__deve_retornar_usuario_do_repositorio_ao_se_salvar_novo_usuario_com_sucesso() throws Exception {
        UsuarioForm usuarioForm = criarUsuarioForm(null);
        Usuario usuarioLogado = mock(Usuario.class);
        
        when(buscadorDeUsuario.getUsuarioLogado()).thenReturn(usuarioLogado);
        when(buscadorDeUnidade.getPorIds(any())).thenAnswer(GerenciadorDeUsuarioTest::unidades);
        when(perfilRepository.getPorIds(any())).thenAnswer(GerenciadorDeUsuarioTest::perfis);
        
        when(usuarioRepository.put(any())).thenAnswer(invocationOnMock -> {
            Usuario user = invocationOnMock.getArgumentAt(0, Usuario.class);
            user.setId(123);
            return user;  
        });

        Usuario resultado = gerenciador.salvar(usuarioForm);

        assertThat(resultado.getId(), is(123));
        assertThat(resultado.getCpf(), is(usuarioForm.getCpf()));
        assertThat(resultado.getEmail(), is(usuarioForm.getEmail()));
        assertThat(resultado.getLogin(), is(usuarioForm.getLogin()));
        assertThat(resultado.getNome(), is(usuarioForm.getNome()));
        assertThat(resultado.getSiape(), is(usuarioForm.getSiape()));
        assertThat(resultado.getTelefone(), is(usuarioForm.getTelefone()));

        assertThat(resultado.getPerfis(), hasSize(usuarioForm.getPerfis().size()));
        resultado.getPerfis().forEach(p -> assertThat(p.getId(), isIn(usuarioForm.getPerfis())));

        assertThat(resultado.getUnidades(), hasSize(usuarioForm.getUnidades().size()));
        resultado.getUnidades().forEach(u -> assertThat(u.getId(), isIn(usuarioForm.getUnidades())));
    }

    @Test
    public void salvar__deve_retornar_usuario_do_repositorio_ao_se_salvar_usuario_ja_existente_com_sucesso() throws Exception {
        UsuarioForm usuarioForm = criarUsuarioForm(123);
        Usuario usuarioLogado = mock(Usuario.class);
        Usuario usuarioJaExistenteNoBD = new Usuario();
        usuarioJaExistenteNoBD.setId(123);
        
        when(buscadorDeUsuario.getUsuarioLogado()).thenReturn(usuarioLogado);
        when(buscadorDeUsuario.getParaEdicao(any())).thenReturn(usuarioJaExistenteNoBD);
        when(buscadorDeUnidade.getPorIds(any())).thenAnswer(GerenciadorDeUsuarioTest::unidades);
        when(perfilRepository.getPorIds(any())).thenAnswer(GerenciadorDeUsuarioTest::perfis);
        
        when(usuarioRepository.put(any())).thenAnswer(
                invocationOnMock -> invocationOnMock.getArgumentAt(0, Usuario.class));

        Usuario resultado = gerenciador.salvar(usuarioForm);

        assertThat(resultado.getId(), is(usuarioForm.getId()));
        assertThat(resultado.getCpf(), is(usuarioForm.getCpf()));
        assertThat(resultado.getEmail(), is(usuarioForm.getEmail()));
        assertThat(resultado.getLogin(), is(usuarioForm.getLogin()));
        assertThat(resultado.getNome(), is(usuarioForm.getNome()));
        assertThat(resultado.getSiape(), is(usuarioForm.getSiape()));
        assertThat(resultado.getTelefone(), is(usuarioForm.getTelefone()));
        
        assertThat(resultado.getPerfis(), hasSize(usuarioForm.getPerfis().size()));
        resultado.getPerfis().forEach(p -> assertThat(p.getId(), isIn(usuarioForm.getPerfis())));

        assertThat(resultado.getUnidades(), hasSize(usuarioForm.getUnidades().size()));
        resultado.getUnidades().forEach(u -> assertThat(u.getId(), isIn(usuarioForm.getUnidades())));
    }

    @Test
    public void alterarDadosUsuarioLogado__deve_alterar_dados_do_usuario_logado() throws Exception {
        Usuario usuarioLogado = new Usuario();
        usuarioLogado.setEmail("emailVelho@cgu.gov.br");
        usuarioLogado.setTelefone("6133213030");
        usuarioLogado.setChaveApi("chaveApiVelha");
        usuarioLogado.setFrequenciaRecebimentoEmail((byte) 1);

        UsuarioLogadoForm usuarioLogadoForm = criarUsuarioLogadoForm();

        when(buscadorDeUsuario.getUsuarioLogado()).thenReturn(usuarioLogado);

        gerenciador.alterarDadosUsuarioLogado(usuarioLogadoForm);

        assertThat(usuarioLogado.getEmail(), is("emailNovo@cgu.gov.br"));
        assertThat(usuarioLogado.getTelefone(), is("6120202020"));
        assertThat(usuarioLogado.getChaveApi(), is("chaveApiNova"));
        assertThat(usuarioLogado.getFrequenciaRecebimentoEmail(), is((byte) 1));

    }

    @Test
    public void toggleAtivo__deve_inverter_flg_ativo() throws Exception {
        Usuario usuarioLogado = mock(Usuario.class);
        Usuario usuarioDoRep = new Usuario();
        usuarioDoRep.setAtivo(true);

        when(usuarioRepository.get(123)).thenReturn(usuarioDoRep);
        when(buscadorDeUsuario.getUsuarioLogado()).thenReturn(usuarioLogado);
        when(usuarioRepository.put(any())).thenAnswer(invocation -> {
            assertThat(((Usuario)invocation.getArguments()[0]).isAtivo(), is(false));
            return null;
        });

        gerenciador.alternarAtivacao(123);
    }

    @Test
    public void toggleAtivo__deve_inverter_flg_inativo() throws Exception {
        Usuario usuarioLogado = mock(Usuario.class);
        Usuario usuarioDoRep = new Usuario();
        usuarioDoRep.setAtivo(false);

        when(usuarioRepository.get(123)).thenReturn(usuarioDoRep);
        when(buscadorDeUsuario.getUsuarioLogado()).thenReturn(usuarioLogado);
        when(usuarioRepository.put(any())).thenAnswer(invocation -> {
            assertThat(((Usuario)invocation.getArguments()[0]).isAtivo(), is(true));
            return null;
        });

        gerenciador.alternarAtivacao(123);
    }

    @Test
    public void criarUsuarioAcesso__deve_criar_usuario_com_sucesso() {
        Usuario usuarioLogado = mock(Usuario.class);

        when(buscadorDeUsuario.getUsuarioLogado()).thenReturn(usuarioLogado);
        when(buscadorDeUnidade.getPorIds(any())).thenAnswer(GerenciadorDeUsuarioTest::unidadesAcesso);
        when(perfilRepository.getPorIds(any())).thenAnswer(GerenciadorDeUsuarioTest::perfisAcesso);
        when(perfilRepository.getPorNomes(any())).thenAnswer(GerenciadorDeUsuarioTest::perfisAcesso);

        when(usuarioRepository.put(any())).thenAnswer(invocationOnMock -> {
            Usuario user = invocationOnMock.getArgumentAt(0, Usuario.class);
            user.setId(123);
            return user;
        });

        UsuarioAcessoForm usuarioAcessoForm = criarUsuarioAcessoForm();
        Usuario resultado = gerenciador.criarUsuarioAcesso(usuarioAcessoForm);

        assertThat(resultado.getId(), is(123));
        assertThat(resultado.getCpf(), is(usuarioAcessoForm.getCpf()));
        assertThat(resultado.getEmail(), is(usuarioAcessoForm.getEmail()));
        assertThat(resultado.getLogin(), is(usuarioAcessoForm.getLogin()));
        assertThat(resultado.getNome(), is(usuarioAcessoForm.getNome()));
        assertThat(resultado.getTelefone(), is(usuarioAcessoForm.getTelefone()));
        assertThat(resultado.isAtivo(), is(true));
        assertThat(resultado.getDataUltimoLogin(), is(greaterThan(LocalDateTime.now().minusMinutes(1))));

        assertThat(resultado.getPerfis(), hasSize(usuarioAcessoForm.getPerfil().size()));
        resultado.getPerfis().forEach(p -> assertThat(p.getNome(), isIn(usuarioAcessoForm.getPerfil())));

        assertThat(resultado.getUnidades(), hasSize(1));
        resultado.getUnidades().forEach(u -> assertThat(u.getId(), is(usuarioAcessoForm.getIdUnidadeLotacao())));
    }

    private UsuarioForm criarUsuarioForm(Integer idUsuario) {
        UsuarioForm usuarioForm = new UsuarioForm();
        usuarioForm.setId(idUsuario);
        usuarioForm.setCpf("00000000079");
        usuarioForm.setEmail("mail@cgu.gov.br");
        usuarioForm.setLogin("CGU\\test_user");
        usuarioForm.setNome("Test User da Silva");
        usuarioForm.setSiape("01455694");
        usuarioForm.setTelefone("20207000");
        List<Integer> idPerfis = asList(1, 2);
        usuarioForm.setPerfis(idPerfis);
        List<Integer> idUnidades = asList(1, 2);
        usuarioForm.setUnidades(idUnidades);
        return usuarioForm;
    }

    private static Object perfis(InvocationOnMock invocationOnMock) {
        return asList(new Perfil(1, "1"), new Perfil(2, "1"));
    }

    private static Object perfisAcesso(InvocationOnMock invocationOnMock) {
        return asList(new Perfil(1, "Gerenciar Trilhas"), new Perfil(2, "Gerenciar Subclasses"));
    }

    private static Object unidades(InvocationOnMock invocationOnMock) {
        Unidade u1 = new Unidade();
        u1.setId(1);

        Unidade u2 = new Unidade();
        u2.setId(2);

        Unidade u3 = new Unidade();
        u3.setId(3);
        u3.setUnidadeSuperior(u1);

        u1.getUnidadesFilhas().add(u3);

        return asList(u1, u2);
    }

    private static Object unidadesAcesso(InvocationOnMock invocationOnMock) {
        Unidade u1 = new Unidade();
        u1.setId(1);

        return Collections.singletonList(u1);
    }

    private UsuarioLogadoForm criarUsuarioLogadoForm() {
        UsuarioLogadoForm usuarioLogadoForm = new UsuarioLogadoForm();
        usuarioLogadoForm.setEmailUsuarioLogado("emailNovo@cgu.gov.br");
        usuarioLogadoForm.setTelefoneUsuarioLogado("6120202020");
        usuarioLogadoForm.setChaveApiUsuarioLogado("chaveApiNova");
        usuarioLogadoForm.setFrequenciaRecebimentoEmail((byte) 1);

        return usuarioLogadoForm;
    }

    private UsuarioAcessoForm criarUsuarioAcessoForm() {
        UsuarioAcessoForm usuarioAcessoForm = new UsuarioAcessoForm();
        usuarioAcessoForm.setCpf("28057098880");
        usuarioAcessoForm.setNome("Mark Lilla");
        usuarioAcessoForm.setLogin("marklilla");
        usuarioAcessoForm.setIdUnidadeLotacao(1);
        usuarioAcessoForm.setEmail("mark.lilla@cgu.gov.br");
        usuarioAcessoForm.setTelefone("(61) 9999-9999");
        usuarioAcessoForm.setPerfil(Arrays.asList("Gerenciar Trilhas", "Gerenciar Subclasses"));

        return usuarioAcessoForm;
    }
}