package br.gov.cgu.mbt.aplicacao.avaliacao.questionario;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.gov.cgu.mbt.negocio.avaliacao.questionario.QRespostaQuestionario;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.RespostaQuestionario;
import br.gov.cgu.persistencia.jpa.RepositoryJpa;

@Repository
public class RespostaQuestionarioRepository extends RepositoryJpa<RespostaQuestionario, Integer> {

	private QRespostaQuestionario respostaQuestionario = QRespostaQuestionario.respostaQuestionario;
	
	public List<RespostaQuestionario> getPorIdAvaliacao(Integer idAvaliacao) {
		return getJPAQuery()
				.selectFrom(respostaQuestionario)
				.where(respostaQuestionario.avaliacao.id.eq(idAvaliacao))
				.fetch();
	}
	
}
