package br.gov.cgu.projetoexemplosb;

import br.gov.cgu.projetoexemplosb.aplicacao.auth.BuscadorDeUsuario;
import br.gov.cgu.projetoexemplosb.infraestrutura.referenciavel.RepositoryNaoEncontradoException;
import br.gov.cgu.projetoexemplosb.web.ControllerAdvice;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ExtendedModelMap;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

public class ControllerAdviceTest {

    private BuscadorDeUsuario usuarioService = mock(BuscadorDeUsuario.class);
    private ControllerAdvice controller = new ControllerAdvice(usuarioService);

    @Test
    public void naoEncontrada_deve_retornar_view() throws Exception {
        String modelAndView = controller.naoEncontrada();

        assertThat(modelAndView, is("error/404"));
    }

    @Test
    public void autocompleteNaoConfiguradoCorretamente__deve_retornar_500() throws Exception {
        SecurityContextHolder.createEmptyContext();
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("sadsa", "dsads"));
        String  modelAndView = controller.autocompleteNaoConfiguradoCorretamente(new ExtendedModelMap(), new RepositoryNaoEncontradoException("Xxxx", new RuntimeException("ola")));

        assertThat(modelAndView, is("error/index"));
    }

    @Test
    public void erroDeIntegridadeReferencial__deve_retornar_integridade_referencial() throws Exception {
        String modelAndView = controller.erroDeIntegridadeReferencial(new JpaSystemException( new RuntimeException(new ConstraintViolationException(null, new SQLException("Teste"), null))));

        assertThat(modelAndView, is("Não é possível excluir um elemento já associado a outra entidade no sistema."));
    }

    @Test
    public void erroDeIntegridadeReferencial__deve_retornar_500() throws Exception {
        String modelAndView = controller.erroDeIntegridadeReferencial(new JpaSystemException( new RuntimeException("xpto")));

        assertThat(modelAndView, is("xpto; nested exception is java.lang.RuntimeException: xpto"));
    }
}