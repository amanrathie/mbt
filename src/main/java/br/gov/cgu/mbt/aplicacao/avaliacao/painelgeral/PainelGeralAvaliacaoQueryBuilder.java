package br.gov.cgu.mbt.aplicacao.avaliacao.painelgeral;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.sql.JPASQLQuery;

import br.gov.cgu.mbt.aplicacao.avaliacao.AvaliacaoDTO;
import br.gov.cgu.mbt.aplicacao.avaliacao.AvaliacaoFiltro;
import br.gov.cgu.mbt.negocio.sqlentities.SAvaliacao;
import br.gov.cgu.persistencia.querybuilder.QueryBuilderJPASQL;

public class PainelGeralAvaliacaoQueryBuilder extends QueryBuilderJPASQL<AvaliacaoFiltro, AvaliacaoDTO> {
	
	private SAvaliacao avaliacao = SAvaliacao.Avaliacao;

	@Override
	public JPASQLQuery<AvaliacaoDTO> gerarQuery(AvaliacaoFiltro filtro) {
		JPASQLQuery<AvaliacaoDTO> query = new JPASQLQuery<>(entityManager, sqlTemplate);
		
		query
		.select(avaliacao.nomAvaliacao)
		.from(avaliacao)
		.orderBy(avaliacao.idTipoAvaliacao.asc());
		
		return query;
	}

	@Override
	public Expression<? extends Comparable> getOrderByExpression(String coluna) {
		// TODO Auto-generated method stub
		return null;
	}

}
