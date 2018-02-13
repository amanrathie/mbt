package br.gov.cgu.mbt.negocio.local;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.gov.cgu.persistencia.jpa.RepositoryJpa;

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
    
    public Municipio getPorCodigoIBGE(Integer codIBGE) {
    	return getJPAQuery()
    			.selectFrom(municipio)
    			.where(municipio.codIBGE.eq(codIBGE))
    			.fetchOne();
    }
}
