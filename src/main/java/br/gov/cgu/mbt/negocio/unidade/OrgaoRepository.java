package br.gov.cgu.mbt.negocio.unidade;

import br.gov.cgu.mbt.negocio.unidade.QOrgao;
import br.gov.cgu.persistencia.jpa.RepositoryJpa;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrgaoRepository extends RepositoryJpa<Orgao, Integer> {

    private QOrgao orgao = QOrgao.orgao;

    public List<Orgao> getPorTermo(String q) {
        return getJPAQuery()
                .selectFrom(orgao)
                .where(
                        orgao.nome.contains(q)
                                .or(orgao.sigla.contains(q))
                                .or(orgao.codigo.eq(q))
                ).limit(10)
                .fetch();
    }

    public List<Orgao> getPorIds(List<Integer> ids) {
        return getJPAQuery()
                .selectFrom(orgao)
                .where(orgao.id.in(ids))
                .fetch();
    }
}
