package br.gov.cgu.mbt.aplicacao.avaliacao.resultado;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.gov.cgu.mbt.negocio.avaliacao.resultado.QResultadoAvaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.resultado.ResultadoAvaliacao;
import br.gov.cgu.persistencia.jpa.RepositoryJpa;

@Repository
public class ResultadoAvaliacaoRepository extends RepositoryJpa<ResultadoAvaliacao, Integer> {
	
	private QResultadoAvaliacao resultadoAvaliacao = QResultadoAvaliacao.resultadoAvaliacao;
	
	public List<ResultadoAvaliacao> getPorIdAvaliacao(Integer idAvaliacao) {
		return getJPAQuery()
				.selectFrom(resultadoAvaliacao)
				.where(resultadoAvaliacao.avaliacao.id.eq(idAvaliacao))
				.fetch();
	}

}
