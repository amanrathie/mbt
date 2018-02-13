package br.gov.cgu.mbt.aplicacao.avaliacao.painelgeral;

import org.springframework.stereotype.Service;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.sql.JPASQLQuery;

import br.gov.cgu.mbt.negocio.sqlentities.SAvaliacao;
import br.gov.cgu.persistencia.querybuilder.QueryBuilderJPASQL;

import br.gov.cgu.mbt.aplicacao.avaliacao.painelgeral.QPainelGeralAvaliacaoDTO;

@Service
public class PainelGeralAvaliacaoQueryBuilder extends QueryBuilderJPASQL<PainelGeralAvaliacaoFiltro, PainelGeralAvaliacaoDTO> {
	
	private SAvaliacao avaliacao = SAvaliacao.Avaliacao;

	@Override
	public JPASQLQuery<PainelGeralAvaliacaoDTO> gerarQuery(PainelGeralAvaliacaoFiltro filtro) {
		JPASQLQuery<PainelGeralAvaliacaoDTO> query = new JPASQLQuery<>(entityManager, sqlTemplate);
		
		query
		.select(new QPainelGeralAvaliacaoDTO(avaliacao.nomAvaliacao, avaliacao.idTipoAvaliacao, avaliacao.idFase)
								
				)
		.from(avaliacao);
		
		filtrarSePreenchido(query, filtro.getTipo(), x -> avaliacao.idTipoAvaliacao.eq(filtro.getTipo().ordinal()));
		//.orderBy(avaliacao.idTipoAvaliacao.asc());		
		return query;
	}

	@Override
	public Expression<? extends Comparable> getOrderByExpression(String coluna) {
		// TODO Auto-generated method stub
		return avaliacao.idAvaliacao;
	}

}
