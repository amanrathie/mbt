package br.gov.cgu.projetoexemplosb.negocio.nucleo;

import br.gov.cgu.persistencia.jpa.RepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MunicipioRepository extends RepositoryJpa<Municipio, Integer> {

    private QMunicipio municipio = QMunicipio.municipio;

    public List<Municipio> getPorTermo(String q) {
        return getJPAQuery()
                .selectFrom(municipio)
                .where(municipio.descricao.contains(q))
                .limit(10)
                .fetch();
    }

    public List<Municipio> getPorIds(List ids) {
        return getJPAQuery()
                .selectFrom(municipio)
                .where(municipio.id.in(ids))
                .orderBy(municipio.id.asc())
                .fetch();
    }
}
