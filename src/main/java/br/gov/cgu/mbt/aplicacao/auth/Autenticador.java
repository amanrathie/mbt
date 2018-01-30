package br.gov.cgu.mbt.aplicacao.auth;

import br.gov.cgu.mbt.negocio.auth.Usuario;
import br.gov.cgu.mbt.negocio.auth.UsuarioExcluidoException;
import br.gov.cgu.mbt.negocio.auth.UsuarioRepository;
import br.gov.cgu.persistencia.jpa.JPAUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.gov.cgu.mbt.Constantes.QTDE_MAXIMA_DIAS_INATIVIDADE_USUARIO;

import java.time.LocalDateTime;

@Service
public class Autenticador {

    private final UsuarioRepository usuarios;

    @Autowired
    public Autenticador(UsuarioRepository usuarios) {
        this.usuarios = usuarios;
    }

    @Transactional
    public Authentication authenticate(Authentication authentication) {
        Usuario usuario = usuarios.getPorLogin(authentication.getName());
        if (usuario == null) {
            throw new CredenciaisInvalidasException();
        }

        if (!usuario.isAtivo()) {
            throw new UsuarioExcluidoException();
        }

        if (usuario.getDataUltimoLogin() != null &&
                usuario.getDataUltimoLogin().plusDays(QTDE_MAXIMA_DIAS_INATIVIDADE_USUARIO).isBefore(LocalDateTime.now())) {
            throw new UsuarioInativadoPorTempoException();
        }

        usuario.setDataUltimoLogin(LocalDateTime.now());
        usuario = usuarios.put(usuario);

        JPAUtils.initializeObject(usuario);

        return new UsernamePasswordAuthenticationToken(usuario, "", usuario.getAuthorities());
    }
}