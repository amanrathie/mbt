package br.gov.cgu.projetoexemplosb;

import br.gov.cgu.projetoexemplosb.web.LoginController;
import br.gov.cgu.projetoexemplosb.aplicacao.auth.Autenticador;
import br.gov.cgu.test.mvc.ControllerTest;
import org.junit.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class LoginControllerTest extends ControllerTest {

    private Autenticador autenticador = mock(Autenticador.class);
    private LoginController controller = new LoginController(autenticador);

    @Override
    public Object getController() {
        return controller;
    }

    @Test
    public void exibirTelaLogin_deve_retornar_view() throws Exception {
        System.setProperty("spring.profiles.active", "teste");
        mockMvc.perform(get("/login"))
               .andExpect(view().name("index/login"));
    }

    @Test
    public void exibirTelaLogin_deve_adicionar_mensagem_se_houver_exception_na_session() throws Exception {
        System.setProperty("spring.profiles.active", "teste");
        MvcResult mvcResult = mockMvc.perform(get("/login")
                                                      .sessionAttr("SPRING_SECURITY_LAST_EXCEPTION", new Exception("Teste")))
                                     .andExpect(model().attribute("alerta", "Teste"))
                                     .andExpect(view().name("index/login"))
                                     .andReturn();

        Object exception = mvcResult.getRequest().getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        assertThat(exception, is(nullValue()));
    }

    @Test
    public void realizarLogin__deve_setar_usuario_no_contexto() throws Exception {
        System.setProperty("spring.profiles.active", "teste");
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.setContext(securityContext);
        when(autenticador.authenticate(any())).thenReturn(authentication);

        mockMvc.perform(post("/login").param("username","teste"))
                .andExpect(request().sessionAttribute("principal", "teste"))
                .andExpect(status().isOk());

        verify(securityContext).setAuthentication(authentication);

        SecurityContextHolder.clearContext();
    }
}