package br.gov.cgu.mbt.negocio.log;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.gov.cgu.mbt.negocio.usuario.Usuario;

public class RevisaoListener implements RevisionListener {

    @Override
    public void newRevision(Object objeto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            Usuario usuario = (Usuario) auth.getPrincipal();

            Revisao revisao = (Revisao) objeto;
            revisao.setIdUsuario(usuario.getId());
        }
    }
}
