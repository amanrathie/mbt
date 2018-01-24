package br.gov.cgu.projetoexemplosb.infraestrutura.auth;


import br.gov.cgu.projetoexemplosb.aplicacao.auth.Autenticador;
import br.gov.cgu.projetoexemplosb.negocio.auth.Usuario;
import br.gov.cgu.projetoexemplosb.negocio.auth.UsuarioExcluidoException;
import br.gov.cgu.projetoexemplosb.negocio.auth.UsuarioRepository;
import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AutenticadorTest {

    private UsuarioRepository repository = mock(UsuarioRepository.class);
    private Autenticador service = new Autenticador(repository);

    @Test(expected = AuthenticationException.class)
    public void tryAuth() throws Exception {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("usuario", "senha-errada");

        service.authenticate(token);
    }


    @Test(expected = UsuarioExcluidoException.class)
    public void auth_deve_lancar_exception_se_usuario_excluido()  {
        Usuario mock = mock(Usuario.class);
        when(mock.isAtivo()).thenReturn(false);
        when(repository.getPorLogin("usuario")).thenReturn(mock);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("usuario", "senha");

        service.authenticate(token);
    }

    @Test
    public void auth_deve_autenticar_com_sucesso_usuario_ativo() throws Exception {
    	// given
    	Usuario usuarioMock = mock(Usuario.class);
    	UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("usuario", "senha");
    	
    	// when
    	when(usuarioMock.isAtivo()).thenReturn(true);
    	when(repository.getPorLogin("usuario")).thenReturn(usuarioMock);
        when(repository.put(usuarioMock)).thenReturn(usuarioMock);

        // then
        Authentication authenticate = service.authenticate(token);
        assertThat(authenticate.isAuthenticated(), is(true));
    }

}