package br.gov.cgu.projetoexemplosb.negocio.log;

import br.gov.cgu.projetoexemplosb.negocio.auth.Usuario;
import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
