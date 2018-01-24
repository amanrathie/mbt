package br.gov.cgu.projetoexemplosb.infraestrutura.adfs;

import br.gov.cgu.projetoexemplosb.aplicacao.auth.Autenticador;
import br.gov.cgu.projetoexemplosb.negocio.auth.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml.SAMLProcessingFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdfsProcessingFilter extends SAMLProcessingFilter {

    private Autenticador autenticador;

    public Autenticador getAutenticador() {
        return autenticador;
    }

    @Autowired
    public void setAutenticador(Autenticador autenticador) {
        this.autenticador = autenticador;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Authentication authentication = super.attemptAuthentication(request, response);
        authentication = autenticador.authenticate(authentication);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        HttpSession session = request.getSession(false);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
        Usuario principal = (Usuario) authentication.getPrincipal();
        session.setAttribute("principal", principal.getLogin());
        session.setAttribute("authentication", authentication);
        return authentication;
    }

}