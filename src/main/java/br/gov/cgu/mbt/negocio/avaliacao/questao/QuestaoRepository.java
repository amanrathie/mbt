package br.gov.cgu.mbt.negocio.avaliacao.questao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;
import br.gov.cgu.persistencia.jpa.RepositoryJpa;

@Repository
public class QuestaoRepository extends RepositoryJpa<Questao, Integer> {
	
	/*private QQuestao questao = QQuestao.questao;
	
	public List<Questao> getPorAvaliacao(Avaliacao avaliacao) {
		return getJPAQuery()
                .selectFrom(questao)
                .where(
                		questao.bloco.avaliacao.eq(avaliacao)
                ).fetch();
	}*/

}
