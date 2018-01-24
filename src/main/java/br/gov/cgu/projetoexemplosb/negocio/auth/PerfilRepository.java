package br.gov.cgu.projetoexemplosb.negocio.auth;

import br.gov.cgu.persistencia.jpa.RepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PerfilRepository extends RepositoryJpa<Perfil, Integer> {
    private QPerfil perfil = QPerfil.perfil;

    public List<Perfil> buscarTodosOrdenadosPorNome() {
        return getJPAQuery()
                .selectFrom(perfil)
                .orderBy(perfil.nome.asc())
                .fetch();
    }
    
    public List<Perfil> getPorIds(List<Integer> ids) {
        return getJPAQuery()
                .selectFrom(perfil)
                .where(perfil.id.in(ids))
                .orderBy(perfil.nome.asc())
                .fetch();
    }

    public List<Perfil> buscarTodosPublicoExterno() {
        return getJPAQuery()
                .selectFrom(perfil)
                .where(perfil.oferecidoPublicoExterno.isTrue())
                .orderBy(perfil.nome.asc())
                .fetch();
    }

    public List<Perfil> getPorNomes(List<String> nomes) {
        return getJPAQuery()
                .selectFrom(perfil)
                .where(perfil.nome.in(nomes))
                .orderBy(perfil.nome.asc())
                .fetch();
    }
}
