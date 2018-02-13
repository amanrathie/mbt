package br.gov.cgu.mbt.aplicacao.entidadeavaliadora;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.gov.cgu.mbt.Constantes;
import br.gov.cgu.mbt.negocio.entidadeavaliadora.EntidadeAvaliadora;
import br.gov.cgu.mbt.negocio.entidadeavaliadora.QEntidadeAvaliadora;
import br.gov.cgu.persistencia.jpa.RepositoryJpa;

@Repository
public class EntidadeAvaliadoraRepository extends RepositoryJpa<EntidadeAvaliadora, Integer> {
	
	private QEntidadeAvaliadora entidadeAvaliadora = QEntidadeAvaliadora.entidadeAvaliadora;
	
	public List<EntidadeAvaliadora> getPorTermo(String q) {
		return getJPAQuery()
				.selectFrom(entidadeAvaliadora)
				.where(entidadeAvaliadora.nome.contains(q))
				.limit(Constantes.QTDE_MAXIMA_REGISTROS_AUTOCOMPLETE)
				.fetch();
	}

}
