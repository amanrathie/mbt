package br.gov.cgu.projetoexemplosb.web.auth;

import br.gov.cgu.projetoexemplosb.aplicacao.auth.BuscadorDeUsuario;
import br.gov.cgu.projetoexemplosb.negocio.auth.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class APIAuthenticationFilter extends OncePerRequestFilter {

    public static final String HEADER_CHAVE_API = "chave-api";

    @Autowired private BuscadorDeUsuario buscador;

    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String chaveApi = servletRequest.getHeader(HEADER_CHAVE_API);
        Authentication authenticationAntes = SecurityContextHolder.getContext().getAuthentication();

        if (isBlank(chaveApi)) {
            if (authenticationAntes != null && authenticationAntes.isAuthenticated()) {
                filterChain.doFilter(servletRequest, response);
                return;
            } else {
                response.sendError(HttpStatus.FORBIDDEN.value(), "Informe o header 'chave-api'.");
                return;
            }
        }

        Usuario usuario = buscador.getUsuarioPorChaveApi(chaveApi);
        if ( usuario == null) {
            response.sendError(HttpStatus.FORBIDDEN.value(), "A chave de API informada é inválida.");
            return;
        }


        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(usuario, "", usuario.getAuthorities()));

        filterChain.doFilter(servletRequest, response);

        SecurityContextHolder.getContext().setAuthentication(authenticationAntes);
    }
}