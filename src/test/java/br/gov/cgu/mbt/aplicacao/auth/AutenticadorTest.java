package br.gov.cgu.mbt.aplicacao.auth;

import org.junit.Test;
import org.springframework.security.core.Authentication;

import br.gov.cgu.mbt.aplicacao.auth.Autenticador;
import br.gov.cgu.mbt.aplicacao.auth.CredenciaisInvalidasException;
import br.gov.cgu.mbt.aplicacao.auth.UsuarioInativadoPorTempoException;
import br.gov.cgu.mbt.negocio.auth.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AutenticadorTest {

    private final UsuarioRepository usuarioRepository = mock(UsuarioRepository.class);

    private final Autenticador autenticador = new Autenticador(usuarioRepository);

    @Test(expected = CredenciaisInvalidasException.class)
    public void authenticate__deve_lancar_exception_quando_credenciais_invalidas() throws Exception {
        Authentication authenticationMock  = mock(Authentication.class);

        when(authenticationMock.getName()).thenReturn("login");
        when(usuarioRepository.getPorLogin("login")).thenReturn(null);

        autenticador.authenticate(authenticationMock);
    }

    @Test(expected = UsuarioExcluidoException.class)
    public void authenticate__deve_lancar_exception_quando_usuario_inativo() throws Exception {
        Authentication authenticationMock  = mock(Authentication.class);
        Usuario usuarioMock = mock(Usuario.class);

        when(authenticationMock.getName()).thenReturn("login");
        when(usuarioRepository.getPorLogin("login")).thenReturn(usuarioMock);
        when(usuarioMock.isAtivo()).thenReturn(false);

        autenticador.authenticate(authenticationMock);
    }

    @Test(expected = UsuarioInativadoPorTempoException.class)
    public void authenticate__deve_lancar_exception_quando_usuario_inativo_por_tempo_de_acesso_ao_sistema() throws Exception {
        // Given
        Authentication authenticationMock  = mock(Authentication.class);

        Usuario usuario = mockUsuario();
        usuario.setDataUltimoLogin(LocalDateTime.now().minusMonths(6));

        when(authenticationMock.getName()).thenReturn("login");
        when(usuarioRepository.getPorLogin("login")).thenReturn(usuario);

        // When
        autenticador.authenticate(authenticationMock);

        // Then
    }

    @Test
    public void authenticate__deve_autenticar_usuario_quando_for_primeiro_acesso_ao_sistema() throws Exception {
        // Given
        Authentication authenticationMock  = mock(Authentication.class);

        Usuario usuario = mockUsuario();

        when(authenticationMock.getName()).thenReturn("login");
        when(usuarioRepository.getPorLogin("login")).thenReturn(usuario);
        when(usuarioRepository.put(usuario)).thenReturn(usuario);

        // When
        Authentication auth = autenticador.authenticate(authenticationMock);

        // Then
        assertThat(auth.getName(), is("login"));
        assertThat(auth.getCredentials(), is(""));
        assertThat(auth.getAuthorities(), hasSize(1));
        assertThat(auth.getAuthorities(), contains(Permissao.GERENCIAR_TODAS_INSTANCIAS));
    }

    @Test
    public void authenticate__deve_autenticar_usuario_quando_acesso_ao_sistema_nao_expirar_tempo_maximo() throws Exception {
        // Given
        Authentication authenticationMock  = mock(Authentication.class);

        Usuario usuario = mockUsuario();
        usuario.setDataUltimoLogin(LocalDateTime.now());

        when(authenticationMock.getName()).thenReturn("login");
        when(usuarioRepository.getPorLogin("login")).thenReturn(usuario);
        when(usuarioRepository.put(usuario)).thenReturn(usuario);

        // When
        Authentication auth = autenticador.authenticate(authenticationMock);

        // Then
        assertThat(auth.getName(), is("login"));
        assertThat(auth.getCredentials(), is(""));
        assertThat(auth.getAuthorities(), hasSize(1));
        assertThat(auth.getAuthorities(), contains(Permissao.GERENCIAR_TODAS_INSTANCIAS));
    }

    private Usuario mockUsuario() {
        Usuario usuario = new Usuario();
        usuario.setLogin("login");
        usuario.setAtivo(true);
        usuario.setPerfis(new ArrayList<>());
        Perfil admin = new Perfil(1, "Admin");
        admin.getPermissoes().add(Permissao.GERENCIAR_TODAS_INSTANCIAS);
        usuario.getPerfis().add(admin);
        return usuario;
    }

}