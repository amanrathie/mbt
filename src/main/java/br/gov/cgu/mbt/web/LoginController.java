/*
 * Essa controler é usada apenas para os testes de integração.
 * Só devem ser acessíveis se o spring profile "teste" estiver ativo.
 */
package br.gov.cgu.mbt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.gov.cgu.mbt.aplicacao.auth.Autenticador;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
/*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
ESSA CONTROLER SÓ DEVE SER ATIVA EM PROFILE DE TESTE!
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
@Profile("login")
public class LoginController {

    private final Autenticador autenticador;

    @Autowired
    public LoginController(Autenticador autenticador) {
        this.autenticador = autenticador;
    }

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String exibirTelaLogin(Model m, HttpSession session) {
        Exception exception = (Exception) session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        if (exception != null) {
            m.addAttribute("alerta", exception.getMessage());
            session.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        }

        return "index/login";
    }

    @RequestMapping(value="/login", method= RequestMethod.POST)
    public String realizarLogin(@RequestParam String username, HttpSession session, Model m) {
        Authentication auth = autenticador.authenticate(new UsernamePasswordAuthenticationToken(username, ""));
        SecurityContextHolder.getContext().setAuthentication(auth);
        session.setAttribute("principal", username);
        m.addAttribute("sucesso", "Login realizado com sucesso.");
        return "index/login";
    }

}