package br.gov.cgu.projetoexemplosb.negocio.log;

import br.gov.cgu.projetoexemplosb.negocio.auth.Usuario;
import org.junit.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RevisaoListenerTest {

    private RevisaoListener listener = new RevisaoListener();

    @Test
    public void newRevision__deve_adicionar_Usuario_na_revisao() throws Exception {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        Usuario usuario = new Usuario();
        usuario.setId(123);
        when(authentication.getPrincipal()).thenReturn(usuario);

        Revisao objeto = new Revisao();
        listener.newRevision(objeto);

        assertThat(objeto.getIdUsuario(), is(usuario.getId()));
        SecurityContextHolder.clearContext();
    }
}