package br.gov.cgu.mbt.web;

import br.gov.cgu.mbt.aplicacao.auth.BuscadorDeUsuario;
import br.gov.cgu.mbt.infraestrutura.referenciavel.MetodoDoRepositoryException;
import br.gov.cgu.mbt.infraestrutura.referenciavel.RepositoryNaoEncontradoException;
import br.gov.cgu.mbt.negocio.auth.Usuario;
import br.gov.cgu.mbt.negocio.auth.UsuarioSemPerfilException;
import br.gov.cgu.persistencia.jpa.EntidadeNaoEncontradaException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Controller
@org.springframework.web.bind.annotation.ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class ControllerAdvice {

    private final BuscadorDeUsuario buscadorDeUsuario;

    private final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

    @Autowired
    public ControllerAdvice(BuscadorDeUsuario buscadorDeUsuario) {this.buscadorDeUsuario = buscadorDeUsuario;}

    @ModelAttribute(value = "usuarioLogado", binding = false, name = "usuarioLogado")
    public Usuario getUsuarioLogado() {
        return buscadorDeUsuario.getUsuarioLogado();
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String naoEncontrada() {
        return "error/404";
    }

    @ExceptionHandler({UsuarioSemPerfilException.class, AuthenticationException.class, AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String semPermissao() {
        return "error/403";
    }

    @ExceptionHandler(JpaSystemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String erroDeIntegridadeReferencial(JpaSystemException e) {
        if (e.getMostSpecificCause().getMessage().contains("Unique index")) {
            return "Já existe alguém com esse identificador cadastrado! Escolha outro.";
        }
        if (e.getCause().getCause() instanceof ConstraintViolationException) {
            return "Não é possível excluir um elemento já associado a outra entidade no sistema.";
        }
        logger.error(e.getMessage(), e);
        return e.getMessage();
    }

    @ExceptionHandler({MetodoDoRepositoryException.class, RepositoryNaoEncontradoException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String autocompleteNaoConfiguradoCorretamente(Model m, Throwable e) {
        String uniqueID = UUID.randomUUID().toString().split("-")[4];
        m.addAttribute("uniqueID", uniqueID);
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            String usuario = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            logger.error(uniqueID + " {" + usuario + "}", e);
        } else {
            logger.error(uniqueID, e);
        }
        return "error/index";
    }

}