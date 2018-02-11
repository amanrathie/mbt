package br.gov.cgu.mbt.negocio.avaliacao.resultado;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.gov.cgu.mbt.negocio.avaliacao.QResultadoAvaliacao;
import br.gov.cgu.persistencia.jpa.RepositoryJpa;

@Repository
public class ResultadoAvaliacaoRepository extends RepositoryJpa<ResultadoAvaliacao, Integer> {
	
	private QResultadoAvaliacao resultadoAvaliacao = QResultadoAvaliacao.resultadoAvaliacao;
	
	public List<ResultadoAvaliacao> getPorNomeMunicipio(String nomeMunicipio) {
		return getJPAQuery()
				.selectFrom(resultadoAvaliacao)
				.where(resultadoAvaliacao.nomeMunicipio.equalsIgnoreCase(nomeMunicipio))
				.fetch();
	}

}
