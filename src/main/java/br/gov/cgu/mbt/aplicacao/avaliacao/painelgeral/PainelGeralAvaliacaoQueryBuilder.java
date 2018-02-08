package br.gov.cgu.mbt.aplicacao.avaliacao.painelgeral;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.sql.JPASQLQuery;

import br.gov.cgu.mbt.negocio.sqlentities.SAvaliacao;
import br.gov.cgu.persistencia.querybuilder.QueryBuilderJPASQL;

public class PainelGeralAvaliacaoQueryBuilder extends QueryBuilderJPASQL<PainelGeralAvaliacaoFiltro, PainelGeralAvaliacaoDTO> {
	
	private SAvaliacao avaliacao = SAvaliacao.Avaliacao;

	@Override
	public JPASQLQuery<PainelGeralAvaliacaoDTO> gerarQuery(PainelGeralAvaliacaoFiltro filtro) {
		JPASQLQuery<PainelGeralAvaliacaoDTO> query = new JPASQLQuery<>(entityManager, sqlTemplate);
		
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
