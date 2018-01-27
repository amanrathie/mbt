package br.gov.cgu.mbt.aplicacao.auth;

import br.gov.cgu.mbt.aplicacao.auth.BuscadorDeUsuario;
import br.gov.cgu.mbt.aplicacao.auth.UsuarioFiltro;
import br.gov.cgu.mbt.aplicacao.auth.UsuarioQueryBuilder;
import br.gov.cgu.mbt.negocio.auth.Perfil;
import br.gov.cgu.mbt.negocio.auth.Permissao;
import br.gov.cgu.mbt.negocio.auth.Usuario;
import br.gov.cgu.mbt.negocio.auth.UsuarioRepository;
import br.gov.cgu.mbt.negocio.unidade.Unidade;
import br.gov.cgu.persistencia.querybuilder.RespostaConsulta;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuscadorDeUsuarioTest {

    public static final Perfil ANALISTA_CGU = new Perfil(1, "Analista CGU");
    public static final Perfil ADMINISTRADOR = new Perfil(0, "Administrador");

    private UsuarioQueryBuilder queryBuilder = mock(UsuarioQueryBuilder.class);
    private UsuarioRepository repository = mock(UsuarioRepository.class);

    private BuscadorDeUsuario buscador = new BuscadorDeUsuario(queryBuilder, repository);

    @After
    public void tearDown() throws Exception {
        SecurityContextHolder.clearContext();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void buscar_deve_chamar_queryBuilder() throws Exception {
        RespostaConsulta mock = mock(RespostaConsulta.class);
        when(queryBuilder.build(any())).thenReturn(mock);
        RespostaConsulta respostaConsulta = buscador.buscar(mock(UsuarioFiltro.class));

        assertThat(respostaConsulta, is(mock));
    }

    @Test
    public void obterPorId_retorna_conteudo_do_repository() throws Exception {
        Usuario conteudo = getUsuario();
        when(repository.getEagerLoaded(132)).thenReturn(conteudo);

        Usuario resultado = buscador.getParaEdicao(132);

        assertThat(resultado, is(conteudo));
    }

    private Usuario getUsuario() {
        Usuario usuario = new Usuario();
        usuario.setLogin("admin");
        usuario.setPerfis(singletonList(ADMINISTRADOR));

        return usuario;
    }

    @Test
    public void getUsuarioLogado_retorna_usuario_do_springCOntext() throws Exception {
        Usuario usuario = mockAutenticacao();

        Usuario usuarioLogado = buscador.getUsuarioLogado();

        Assert.assertThat(usuario, is(usuarioLogado));
    }

    private Usuario mockAutenticacao() {
        Usuario usuario = mock(Usuario.class);
        Authentication authentication = new UsernamePasswordAuthenticationToken(usuario,"", singletonList(Permissao.GERENCIAR_TODAS_INSTANCIAS));
        // Mockito.whens() for your authorization object
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        return usuario;
    }

    @Test
    public void getUsuarioLogado_retorna_null_se_principal_nao_for_Usuario() throws Exception {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn("Xpto");
        SecurityContextHolder.setContext(securityContext);

        Usuario usuarioLogado = buscador.getUsuarioLogado();

        Assert.assertThat(usuarioLogado, is(nullValue()));
    }

    @Test
    public void getUsuarioLogado_retorna_null_se_nao_tiver_logado() throws Exception {
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(false);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        Usuario usuarioLogado = buscador.getUsuarioLogado();

        Assert.assertThat(usuarioLogado, is(nullValue()));
    }

    @Test
    public void getUsuarioLogado_retorna_null_authentication_for_null() throws Exception {
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(null);
        Usuario usuarioLogado = buscador.getUsuarioLogado();

        Assert.assertThat(usuarioLogado, is(nullValue()));
    }

    @Test
    public void buscarHierarquiaUnidadesUsuarioLogado__deve_retornar_lista_de_unidades_da_hierarquia_do_usuario_logado() throws Exception {
        Usuario usuario = mockAutenticacao();
        when(repository.buscarHierarquiaUnidadesUsuario(any())).thenReturn(Arrays.asList(new Unidade()));
        List <Unidade> unidades = buscador.buscarHierarquiaUnidadesUsuarioLogado();

        assertThat(unidades, hasSize(1));
    }

    @Test
    public void getUsuarioPorChaveApi_retorna_usuario_do_repositorio() throws Exception {
        Usuario usuario = new Usuario();
        when(repository.getPorChaveApi("ABCD")).thenReturn(usuario);

        Usuario resultado = buscador.getUsuarioPorChaveApi("ABCD");

        assertThat(resultado, is(usuario));
    }

    @Test
    public void getUsuarioPorLogin_retorna_usuario_do_repositorio() throws Exception {
        Usuario usuario = new Usuario();
        when(repository.getPorLogin("rodrigovfs")).thenReturn(usuario);

        Usuario resultado = buscador.buscarPorLogin("rodrigovfs");

        assertThat(resultado, is(usuario));
    }

    @Test
    public void getPorIds__deve_retornar_resultado_do_repository() throws Exception {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        List<Usuario> usuarioRepository = new ArrayList<>();
        when(repository.getPorIds(ids)).thenReturn(usuarioRepository);

        List<Usuario> resultado = buscador.getPorIds(ids);

        assertThat(resultado, is(usuarioRepository));
    }
}