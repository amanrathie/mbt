package br.gov.cgu.mbt.negocio.unidade;

import br.gov.cgu.mbt.negocio.auth.QUsuario;
import br.gov.cgu.mbt.negocio.unidade.QUnidade;
import br.gov.cgu.persistencia.jpa.RepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UnidadeRepository extends RepositoryJpa<Unidade, Integer> {

    private QUnidade unidade = QUnidade.unidade;
    private QUsuario usuario = QUsuario.usuario;

    public List<Unidade> getPorTermo(String q) {
        return getJPAQuery()
                .selectFrom(unidade)
                .where(
                        unidade.nome.contains(q)
                                .or(unidade.sigla.contains(q))
                                .or(unidade.codigo.eq(q))
                ).limit(10)
                .fetch();
    }

    public List<Unidade> getPorIds(List ids) {
        return getJPAQuery()
                .selectFrom(unidade)
                .where(unidade.id.in(ids))
                .fetch();
    }

    @SuppressWarnings("unchecked")
    public boolean isGestor(Integer idUnidade, Integer idUsuario) {
        return getJPAQuery()
                .select(unidade.id.count())
                .from(unidade)
                .innerJoin(unidade.gestores, usuario)
                .where(unidade.id.eq(idUnidade)
                        .and(usuario.id.eq(idUsuario)))
                .fetchCount() > 0;
    }
}
