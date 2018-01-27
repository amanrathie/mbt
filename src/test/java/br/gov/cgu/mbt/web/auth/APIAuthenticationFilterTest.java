package br.gov.cgu.mbt.web.auth;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;

import br.gov.cgu.mbt.aplicacao.auth.BuscadorDeUsuario;
import br.gov.cgu.mbt.negocio.auth.Usuario;
import br.gov.cgu.mbt.web.auth.APIAuthenticationFilter;

import javax.servlet.FilterChain;

import static br.gov.cgu.mbt.web.auth.APIAuthenticationFilter.HEADER_CHAVE_API;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

public class APIAuthenticationFilterTest {

    private BuscadorDeUsuario buscador = mock(BuscadorDeUsuario.class);
    private APIAuthenticationFilter filter = new APIAuthenticationFilter();

    @Before
    public void setUp() throws Exception {
        setField(filter, "buscador", buscador);
    }

    @Test
    public void init_e_destroy_nao_faz_nada() throws Exception {
        filter.init(new MockFilterConfig());
        filter.destroy();
    }

    @Test
    public void doFilter__chama_a_chain_e_finaliza_se_chave_for_vazia() throws Exception {
        MockFilterChain filterChain = new MockFilterChain();
        MockHttpServletResponse servletResponse = new MockHttpServletResponse();
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();

        filter.doFilter(servletRequest, servletResponse, filterChain);
    }

    @Test
    public void doFilter__responseError_se_chave_nao_pertence_a_nenhum_usuario() throws Exception {
        MockFilterChain filterChain = new MockFilterChain();
        MockHttpServletResponse servletResponse = new MockHttpServletResponse();
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();

        servletRequest.addHeader(HEADER_CHAVE_API, "ABACADS");
        filter.doFilter(servletRequest, servletResponse, filterChain);

        assertThat(servletResponse.getErrorMessage(), is("A chave de API informada é inválida."));
        assertThat(servletResponse.getStatus(), is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    public void doFilter__troca_usuario_da_sessao_e_desfaz_apos_o_chain() throws Exception {
        final Usuario usuarioDaChave = new Usuario();
        when(buscador.getUsuarioPorChaveApi("ABACADS")).thenReturn(usuarioDaChave);
        SecurityContextHolder.clearContext();

        MockHttpServletResponse servletResponse = new MockHttpServletResponse();
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        FilterChain filterChain = mock(FilterChain.class);
        doAnswer(invocation -> {
            assertThat(SecurityContextHolder.getContext().getAuthentication().getPrincipal(), is(usuarioDaChave));
            return null;
        }).when(filterChain).doFilter(any(), any());

        servletRequest.addHeader(HEADER_CHAVE_API, "ABACADS");
        filter.doFilter(servletRequest, servletResponse, filterChain);

        assertThat(SecurityContextHolder.getContext().getAuthentication(), is(nullValue()));
    }
}